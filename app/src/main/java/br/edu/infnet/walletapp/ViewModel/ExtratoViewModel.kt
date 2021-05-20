package br.edu.infnet.walletapp.ViewModel


import androidx.lifecycle.*
import br.edu.infnet.walletapp.Model.Lancamentos
import br.edu.infnet.walletapp.R

class ExtratoViewModel: ViewModel() {
    val extrato = MutableLiveData<List<Lancamentos>>()
    val saldo = MutableLiveData<Double>().apply { value = 0.00 }
    var saldoLiveData: Double = 0.00
        set(value) {
            saldo.value = value
            field = value
        }
    val lucro = MutableLiveData<Double>().apply { value = 0.00 }
    var lucroLiveData: Double = 0.00
        set(value) {
            lucro.value = value
            field = value
        }
    val despesa = MutableLiveData<Double>().apply { value = 0.00 }
    var despesaLiveData: Double = 0.00
        set(value) {
            despesa.value = value
            field = value
        }
    val _texto = MutableLiveData<String>().apply {
        value =  R.string.nao_ha_lancamento.toString()
    }
    val texto: LiveData<String> = _texto
}