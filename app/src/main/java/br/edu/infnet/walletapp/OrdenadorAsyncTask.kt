package br.edu.infnet.walletapp

import android.os.AsyncTask
import br.edu.infnet.walletapp.Model.Lancamentos

class OrdenadorAsyncTask(var mLista: List<Lancamentos>)
    : AsyncTask<Void, Unit, List<Lancamentos>>() {
    override fun doInBackground(vararg voids: Void?): List<Lancamentos> {

        return mLista.reversed()
    }

    override fun onPostExecute(result: List<Lancamentos>) {
        mLista = result
    }
}