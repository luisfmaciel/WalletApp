package br.edu.infnet.walletapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CadastroViewModel: ViewModel() {
    val nomeLiveData = MutableLiveData<String>().apply { value = "" }
    var nome: String = ""
        set(value) {
            nomeLiveData.value = value
            field = value
        }
    val emailLiveData = MutableLiveData<String>().apply { value = "" }
    var email: String = ""
        set(value) {
            emailLiveData.value = value
            field = value
        }
    val profissaoLiveData = MutableLiveData<String>().apply { value = "" }
    var profissao: String = ""
        set(value) {
            profissaoLiveData.value = value
            field = value
        }
    var senha: String = ""
}