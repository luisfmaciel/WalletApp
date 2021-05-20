package br.edu.infnet.walletapp.ui

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.edu.infnet.walletapp.*
import br.edu.infnet.walletapp.ViewModel.CadastroViewModel
import kotlinx.android.synthetic.main.fragment_informacao.*
import kotlinx.android.synthetic.main.fragment_perfil.*

class PerfilFragment : Fragment() {


    private val mSharedPrefFile = "br.edu.infnet.walletapp"
    private var mPreferences: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPreferences = context?.getSharedPreferences(mSharedPrefFile, MODE_PRIVATE)
        val nome = mPreferences!!.getString(NOME_KEY, usuario[USER_INDEX].nome)
        val email = mPreferences!!.getString(EMAIL_KEY, usuario[USER_INDEX].email)
        val profissao = mPreferences!!.getString(PROFISSAO_KEY, usuario[USER_INDEX].profissao)

        tvNomePerfilResultado.text = nome
        tvEmailPerfilResultado.text = email
        tvProfissaoPerfilResultado.text = profissao

        setUpListeners()
    }

    override fun onPause() {
        super.onPause()
        val preferencesEditor = mPreferences!!.edit()
        preferencesEditor.putString(NOME_KEY, usuario[USER_INDEX].nome)
        preferencesEditor.putString(EMAIL_KEY, usuario[USER_INDEX].email)
        preferencesEditor.putString(PROFISSAO_KEY, usuario[USER_INDEX].profissao)
        preferencesEditor.apply()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(NOME_KEY, usuario[USER_INDEX].nome)
        outState.putString(EMAIL_KEY, usuario[USER_INDEX].email)
        outState.putString(PROFISSAO_KEY, usuario[USER_INDEX].profissao)
    }

    private fun setUpListeners(){
        fab_add_lancamento_perfil.setOnClickListener {
            CODIGO_ADD_VIA_PERFIL = 1
            findNavController().navigate(R.id.extrato_dest, null)
        }
    }
}