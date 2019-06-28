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
import br.unitins.cinema.model.SQLiteHelperDB
import br.unitins.cinema.model.Sala

class AdapterListaCrudSala(val lst_sala: ArrayList<Sala>, val context: Context) : RecyclerView.Adapter<AdapterListaCrudSala.ImageViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ImageViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.panel_lista_salas, viewGroup, false)

        val imageViewHolder = ImageViewHolder(view)

        return imageViewHolder

    }

    override fun onBindViewHolder(imageViewHolder: ImageViewHolder, i: Int) {

        imageViewHolder.lblNumero!!.text = lst_sala[i].numero.toString()
        imageViewHolder.lblPoltronas!!.text = lst_sala[i].poltronas.toString()

        imageViewHolder.btnDelete!!.setOnClickListener(
            View.OnClickListener {
                val db = SQLiteHelperDB(this.context)

                val dialog = Dialog(this.context)

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.modal_confirma);
                val msg = dialog.findViewById<TextView>(R.id.txt_confirma_mensagem)
                msg.setText("Deseja deletar a sala " + lst_sala[i].numero + "?")
                val btnCancela = dialog.findViewById<Button>(R.id.btn_confirmacao_cancela)
                val btnConfirma = dialog.findViewById<Button>(R.id.btn_confirmacao_confirma)

                btnCancela.setOnClickListener(View.OnClickListener {
                    dialog.dismiss()
                })

                btnConfirma.setOnClickListener(View.OnClickListener {
                    db.deletaSala(lst_sala[i].id)
                    dialog.dismiss()
                    Toast.makeText(this.context, "Sala deletada com sucesso!", Toast.LENGTH_SHORT).show()
                    this.notifyItemRemoved(i)
                    lst_sala.remove(lst_sala[i])
                })

                dialog.show()
            }
        )

    }

    override fun getItemCount(): Int {
        return lst_sala.size
    }


    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var lblNumero: TextView? = null
        var lblPoltronas: TextView? = null
        var btnEditar: Button? = null
        var btnDelete: Button? = null

        init {
            lblNumero = itemView.findViewById(R.id.lbl_lista_salas_numero)
            lblPoltronas = itemView.findViewById(R.id.lbl_lista_salas_poltronas)
            btnEditar = itemView.findViewById(R.id.btn_lista_salas_editar)
            btnDelete = itemView.findViewById(R.id.btn_lista_salas_deletar2)
        }

    }
}