package br.edu.infnet.walletapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.edu.infnet.walletapp.R
import br.edu.infnet.walletapp.ViewModel.CadastroViewModel
import kotlinx.android.synthetic.main.fragment_informacao.*

class InformacaoFragment : Fragment() {

    private lateinit var cadastroViewModel: CadastroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_informacao, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {act->
            cadastroViewModel = ViewModelProviders.of(act)
                .get(CadastroViewModel::class.java)
        }
        subscribe()
    }

    private fun subscribe(){
        cadastroViewModel.nomeLiveData.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrBlank()) {
                tvNomeInfo.visibility = View.VISIBLE
                tvNomeInfoResultado.visibility = View.VISIBLE
                tvNomeInfoResultado.text = it
            } else {
                tvNomeInfo.visibility = View.GONE
                tvNomeInfoResultado.visibility = View.GONE
            }
        })

        cadastroViewModel.emailLiveData.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrBlank()) {
                tvEmailInfo.visibility = View.VISIBLE
                tvEmailInfoResultado.visibility = View.VISIBLE
                tvEmailInfoResultado.text = it
            } else {
                tvEmailInfo.visibility = View.GONE
                tvEmailInfoResultado.visibility = View.GONE
            }
        })

        cadastroViewModel.profissaoLiveData.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrBlank()) {
                tvProfissaoInfo.visibility = View.VISIBLE
                tvProfissaoInfoResultado.visibility = View.VISIBLE
                tvProfissaoInfoResultado.text = it
            } else {
                tvProfissaoInfo.visibility = View.GONE
                tvProfissaoInfoResultado.visibility = View.GONE
            }
        })
    }
}