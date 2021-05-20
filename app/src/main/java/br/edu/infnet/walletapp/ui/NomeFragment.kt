package br.edu.infnet.walletapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.edu.infnet.walletapp.R
import br.edu.infnet.walletapp.ViewModel.CadastroViewModel
import br.edu.infnet.walletapp.usuario
import kotlinx.android.synthetic.main.fragment_nome.*


class NomeFragment : Fragment() {

    private lateinit var cadastroViewModel: CadastroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { act ->
            cadastroViewModel = ViewModelProviders.of(act)
                .get(CadastroViewModel::class.java)
        }
        setUpListners()
    }

    private fun setUpListners() {
        btnAvancarNome.setOnClickListener {
            val nome = etNomeCadastro.text.toString()
            when {
                nome.isEmpty() -> {
                    Toast.makeText(context, "Informe o seu nome!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    cadastroViewModel.nome = nome
                    findNavController().navigate(R.id.email_dest, null)
                }
            }
        }
    }
}