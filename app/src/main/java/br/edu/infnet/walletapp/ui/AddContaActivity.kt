package br.edu.infnet.walletapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import br.edu.infnet.walletapp.*
import kotlinx.android.synthetic.main.activity_add_conta.*
import java.lang.Exception

class AddContaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_conta)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setUpListeners()
    }

    private fun setUpListeners(){
        btn_adicionar_conta.setOnClickListener {
            try {
                val retornoIntent = Intent()
                val descricao = etDescricao.text.toString()
                val data = etDataAdd.text.toString()
                val valor = etValorAdd.text.toString()
                val tipo = onRadioButtonClicked()

                if (descricao.isNotEmpty() && data.isNotEmpty() && valor.isNotEmpty() && tipo.isNotEmpty()) {
                    retornoIntent.putExtra(DESCRICAO_EXTRA, descricao)
                    retornoIntent.putExtra(VALOR_EXTRA, valor)
                    retornoIntent.putExtra(DATA_EXTRA, data)
                    retornoIntent.putExtra(TIPO_EXTRA, tipo)
                    setResult(Activity.RESULT_OK, retornoIntent)
                    finish()
                } else {
                    Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun onRadioButtonClicked() : String {
        val radioButton: RadioButton = rbGroupAddConta.findViewById(rbGroupAddConta.checkedRadioButtonId)
        var opcao = ""
        when (radioButton) {
            rb_entrada -> opcao = ENTRADA
            rb_despesa -> opcao = DESPESA
        }
        return opcao
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        finish()
        return true
    }
}