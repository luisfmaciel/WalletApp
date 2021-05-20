package br.edu.infnet.walletapp.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.infnet.walletapp.*
import br.edu.infnet.walletapp.Model.Lancamentos
import br.edu.infnet.walletapp.ViewModel.ExtratoViewModel
import kotlinx.android.synthetic.main.fragment_extrato.*

class ExtratoFragment : Fragment() {

    private lateinit var extratoViewModel: ExtratoViewModel
    private val ADD_REQUEST_CODE = 71
    private val mSharedPrefFile = "br.edu.infnet.walletapp"
    private var mPreferences: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_extrato, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {act->
            extratoViewModel = ViewModelProviders.of(act)
                .get(ExtratoViewModel::class.java)
        }

        configurarRecyclerView()
        subscribe()
        setUpListeners()
        addLancamentoViaPerfil()
    }

    private fun configurarRecyclerView(){
        lista_extrato.layoutManager = LinearLayoutManager(activity)
        lista_extrato.adapter = ExtratoAdapter()
        lista_extrato.addItemDecoration(
                DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        )
    }

    private fun subscribe(){
        extratoViewModel.extrato.observe(viewLifecycleOwner, Observer { lista ->
            if (lista != null){
                val adapter = lista_extrato.adapter
                if (adapter is ExtratoAdapter){
                    adapter.mudarDados(lista)
                }
            }
        })
        extratoViewModel.saldo.observe(viewLifecycleOwner, Observer {
            if (!it.toString().isBlank()) {
                tvSaldoCarteira.text = String.format("%.2f", it)
            }
        })
        extratoViewModel.lucro.observe(viewLifecycleOwner, Observer {
            if (!it.toString().isBlank()) {
                tvLucroCarteira.text = String.format("%.2f", it)
            }
        })
        extratoViewModel.despesa.observe(viewLifecycleOwner, Observer {
            if (!it.toString().isBlank()) {
                tvDespesaCarteira.text = String.format("%.2f", it)
            }
        })
        extratoViewModel.texto.observe(viewLifecycleOwner, Observer {
            tvListaVazia.text = getText(it.toInt())
        })
    }

    private fun setUpListeners(){
        fab_add_lancamento.setOnClickListener {
            val addIntent = Intent(context, AddContaActivity::class.java)

            startActivityForResult(addIntent, ADD_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int,
                                  resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == ADD_REQUEST_CODE){
                data?.let {
                    val lista = extratoViewModel.extrato.value ?: listOf()
                    val data = it.getStringExtra(DATA_EXTRA).toString()
                    val descricao = it.getStringExtra(DESCRICAO_EXTRA).toString()
                    val tipo = it.getStringExtra(TIPO_EXTRA).toString()
                    var valor = it.getStringExtra(VALOR_EXTRA)!!.toDouble()
                    val conta = Lancamentos(
                        tipo,
                        descricao,
                        valor,
                        data
                    )
                    extratoViewModel.extrato.value = lista + conta
                    extratoViewModel._texto.value = R.string.string_vazia.toString()
                    if (tipo == ENTRADA) {
                        extratoViewModel.saldoLiveData += valor
                        extratoViewModel.lucroLiveData += valor
                    } else {
                        valor = 0 - valor
                        extratoViewModel.saldoLiveData += valor
                        extratoViewModel.despesaLiveData += valor
                    }
                }
            }
        }
    }

    private fun addLancamentoViaPerfil() {
        if (CODIGO_ADD_VIA_PERFIL == 1) {
            fab_add_lancamento.performClick()
            CODIGO_ADD_VIA_PERFIL = 0
        }
    }

    /*override fun onPause() {
        super.onPause()
        return
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        return
    }*/

}