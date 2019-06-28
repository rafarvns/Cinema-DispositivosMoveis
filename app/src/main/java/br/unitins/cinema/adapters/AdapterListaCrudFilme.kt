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
import java.util.ArrayList

class AdapterListaCrudFilme(val lst_filme: ArrayList<Filme>, val context: Context) : RecyclerView.Adapter<AdapterListaCrudFilme.ImageViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ImageViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.panel_crud_filme, viewGroup, false)

        val imageViewHolder = ImageViewHolder(view)

        return imageViewHolder

    }

    override fun onBindViewHolder(imageViewHolder: ImageViewHolder, i: Int) {

        imageViewHolder.lbl_titulo!!.text = lst_filme[i].titulo
        imageViewHolder.img!!.setImageResource(R.drawable.vingadores)

        imageViewHolder.btnDelete!!.setOnClickListener(
            View.OnClickListener {
                val db = SQLiteHelperDB(this.context)

                val dialog = Dialog(this.context)

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.modal_confirma);
                val msg = dialog.findViewById<TextView>(R.id.txt_confirma_mensagem)
                msg.setText("Deseja deletar o filme " + lst_filme[i].titulo + "?")
                val btnCancela = dialog.findViewById<Button>(R.id.btn_confirmacao_cancela)
                val btnConfirma = dialog.findViewById<Button>(R.id.btn_confirmacao_confirma)

                btnCancela.setOnClickListener(View.OnClickListener {
                    dialog.dismiss()
                })

                btnConfirma.setOnClickListener(View.OnClickListener {
                    db.deletaFilme(lst_filme[i].id)
                    dialog.dismiss()
                    Toast.makeText(this.context, "Filme deletado com sucesso!", Toast.LENGTH_SHORT).show()
                    this.notifyItemRemoved(i)
                    lst_filme.remove(lst_filme[i])
                })

                dialog.show()
            }
        )

    }

    override fun getItemCount(): Int {
        return lst_filme.size
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var img: ImageView? = null
        var lbl_titulo: TextView? = null
        var btnDelete: Button? = null

        init {
            img = itemView.findViewById(R.id.img_lista_crud_filme)
            lbl_titulo = itemView.findViewById(R.id.lbl_lista_crud_filme_titulo)
            btnDelete = itemView.findViewById(R.id.btn_lista_crud_filme_deletar)
        }

    }
}

