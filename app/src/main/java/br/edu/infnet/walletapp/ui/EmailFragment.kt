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
import kotlinx.android.synthetic.main.fragment_email.*

class EmailFragment : Fragment() {

    private lateinit var cadastroViewModel: CadastroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_email, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { act ->
            cadastroViewModel = ViewModelProviders.of(act)
                .get(CadastroViewModel::class.java)
        }
        setUpListeners()
    }

    private fun setUpListeners() {
        btnAvancarEmail.setOnClickListener {
            val email = etEmailCadastro.text.toString()
            when {
                email.isEmpty() -> {
                    Toast.makeText(context, "Informe o seu email!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    cadastroViewModel.email = email
                    findNavController().navigate(R.id.profissao_dest, null)
                }
            }
        }
    }
}