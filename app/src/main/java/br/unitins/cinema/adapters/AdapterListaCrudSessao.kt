package br.unitins.cinema.adapters

import android.app.Dialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import br.unitins.cinema.R
import br.unitins.cinema.model.Filme
import br.unitins.cinema.model.SQLiteHelperDB
import br.unitins.cinema.model.Sessao
import java.util.ArrayList

class AdapterListaCrudSessao(val lst_sessao: ArrayList<Sessao>, val context: Context) : RecyclerView.Adapter<AdapterListaCrudSessao.ImageViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ImageViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.panel_lista_sessao, viewGroup, false)

        val imageViewHolder = ImageViewHolder(view)

        return imageViewHolder

    }

    override fun onBindViewHolder(imageViewHolder: ImageViewHolder, i: Int) {

        imageViewHolder.lbl_titulo!!.text = "Filme: "+lst_sessao[i].filme.titulo
        imageViewHolder.lbl_sala!!.text = "Sala: "+lst_sessao[i].sala.numero.toString()
        imageViewHolder.data!!.text = "Data: "+lst_sessao[i].dia.toString()
        imageViewHolder.img!!.setImageResource(R.drawable.vingadores)

        imageViewHolder.btnDelete!!.setOnClickListener(
            View.OnClickListener {
                val db = SQLiteHelperDB(this.context)

                val dialog = Dialog(this.context)

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.modal_confirma);
                val msg = dialog.findViewById<TextView>(R.id.txt_confirma_mensagem)
                msg.setText("Deseja deletar esta sessão?")
                val btnCancela = dialog.findViewById<Button>(R.id.btn_confirmacao_cancela)
                val btnConfirma = dialog.findViewById<Button>(R.id.btn_confirmacao_confirma)

                btnCancela.setOnClickListener(View.OnClickListener {
                    dialog.dismiss()
                })

                btnConfirma.setOnClickListener(View.OnClickListener {
                    db.deleteSessao(lst_sessao[i].id)
                    dialog.dismiss()
                    Toast.makeText(this.context, "Sessão deletada com sucesso!", Toast.LENGTH_SHORT).show()
                    lst_sessao.remove(lst_sessao[i])
                    this.notifyItemRemoved(i)
                })

                dialog.show()
            }
        )

    }

    override fun getItemCount(): Int {
        return lst_sessao.size
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var img: ImageView? = null
        var lbl_titulo: TextView? = null
        var lbl_sala: TextView? = null
        var data: TextView? = null
        var btnDelete: Button? = null

        init {
            img = itemView.findViewById(R.id.img_lista_sessao)
            lbl_titulo = itemView.findViewById(R.id.lbl_lista_sessao_titulo)
            lbl_sala = itemView.findViewById(R.id.lbl_lista_sessao_sala)
            data = itemView.findViewById(R.id.lbl_lista_sessao_data)
            btnDelete = itemView.findViewById(R.id.btn_lista_sessao_deletar)
        }

    }
}