package br.edu.infnet.walletapp.ui


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.walletapp.*
import br.edu.infnet.walletapp.Model.Lancamentos
import kotlinx.android.synthetic.main.extrato_card.view.*

class ExtratoAdapter(var listaDeLancamentos:List<Lancamentos> = listOf()):

    RecyclerView.Adapter<ExtratoAdapter.ExtratoViewholder>() {

    class ExtratoViewholder(itemView: View):
        RecyclerView.ViewHolder(itemView){
        val tvDescricao: TextView = itemView.tvDescricaoExtrato
        val tvData: TextView = itemView.tvDataExtrato
        val tvValor: TextView = itemView.tvValorExtrato
        val tvMoeda: TextView = itemView.tvMoedaExtrato
    }

    override fun getItemCount() = listaDeLancamentos.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): ExtratoViewholder {
        val card = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.extrato_card, parent, false)

        return ExtratoViewholder(card)
    }

    override fun onBindViewHolder(holder: ExtratoViewholder,
                                  position: Int) {
        val addLancamento = listaDeLancamentos[position]

        holder.tvData.text = addLancamento.data
        holder.tvDescricao.text = addLancamento.descricao

        if (addLancamento.tipo == ENTRADA) {
            holder.tvValor.text = String.format("%.2f", addLancamento.valor)
        } else {
            holder.tvValor.text = String.format("%.2f", (0 - addLancamento.valor))
        }

        if (addLancamento.tipo == ENTRADA){
            holder.tvValor.setTextColor(Color.parseColor("#00C853"))
            holder.tvMoeda.setTextColor(Color.parseColor("#00C853"))
        } else {
            holder.tvValor.setTextColor(Color.parseColor("#F50057"))
            holder.tvMoeda.setTextColor(Color.parseColor("#F50057"))
        }
    }

    fun mudarDados(lancamentos: List<Lancamentos>){
        val lista = OrdenadorAsyncTask(lancamentos).execute().get()
        listaDeLancamentos = lista
        notifyDataSetChanged()
    }
}
