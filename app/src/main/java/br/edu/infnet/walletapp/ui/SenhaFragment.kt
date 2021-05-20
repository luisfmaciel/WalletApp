package br.edu.infnet.walletapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import br.edu.infnet.walletapp.*
import br.edu.infnet.walletapp.Model.Usuario
import br.edu.infnet.walletapp.ViewModel.CadastroViewModel
import kotlinx.android.synthetic.main.fragment_senha.*

class SenhaFragment : Fragment() {

    private lateinit var cadastroViewModel: CadastroViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_senha, container, false)
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
        btnSalvarCadastro.setOnClickListener {
            val senha = etSenhaCadastro.text.toString()
            val confirmaSenha = etConfirmaSenhaCadastro.text.toString()

            if (senha.isEmpty() || confirmaSenha.isEmpty()) {
                Toast.makeText(context, "Preencha todos os campos!",
                    Toast.LENGTH_SHORT).show()
            } else {
                if (senha != confirmaSenha) {
                    Toast.makeText(context, "Senhas não correspondentes!",
                        Toast.LENGTH_SHORT).show()
                } else {
                    cadastroViewModel.senha = confirmaSenha
                    val loginIntent = Intent(context, LoginActivity::class.java)

                    loginIntent.putExtra(EMAIL_EXTRA, cadastroViewModel.email)
                    loginIntent.putExtra(SENHA_EXTRA, cadastroViewModel.senha)

                    val novoUsuario = Usuario(
                            cadastroViewModel.nome,
                            cadastroViewModel.email,
                            cadastroViewModel.profissao,
                            cadastroViewModel.senha
                    )
                    usuario.add(USER_INDEX, novoUsuario)

                    startActivity(loginIntent)
                    activity?.finish()
                }
            }
        }
    }
}