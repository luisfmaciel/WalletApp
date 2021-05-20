package br.edu.infnet.walletapp.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.edu.infnet.walletapp.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        confereEmail()
    }

    fun entrar(view: View) {
        val mainIntent = Intent(this, MainActivity::class.java)

        val senhaLogin = intent.getStringExtra(SENHA_EXTRA)
        val emailLogin = intent.getStringExtra(EMAIL_EXTRA)

        if (senhaLogin != null && emailLogin != null) {
            if (etSenhaLogin.text.toString() == senhaLogin.toString()
                    && etEmailLogin.text.toString() == emailLogin.toString()) {
                startActivity(mainIntent)
                finish()
            } else {
                Toast.makeText(this, "Senha ou Login Inválido!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Senha ou Login Inválido!", Toast.LENGTH_SHORT).show()
        }
    }

    fun cadastrar(view: View) {
        val cadastroIntent = Intent(this, CadastroActivity::class.java)
        startActivity(cadastroIntent)
        finish()
    }

        private fun confereEmail() {
            val emailLogin = intent.getStringExtra(EMAIL_EXTRA)

            if (emailLogin != null) {
                etEmailLogin.setText(emailLogin)
            }
        }
}