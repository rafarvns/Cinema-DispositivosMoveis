package br.unitins.cinema.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.unitins.cinema.R
import br.unitins.cinema.model.Filme

import java.util.ArrayList

class AdapterListaFilme(val lst_filme: ArrayList<Filme>, val context: Context) : RecyclerView.Adapter<AdapterListaFilme.ImageViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ImageViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.panel_lista_filme, viewGroup, false)

        val imageViewHolder = ImageViewHolder(view)

        return imageViewHolder

    }

    override fun onBindViewHolder(imageViewHolder: ImageViewHolder, i: Int) {

        imageViewHolder.lbl_titulo!!.text = lst_filme[i].titulo
        imageViewHolder.lbl_horarios!!.text = "Mock"
        imageViewHolder.lbl_sinopse!!.text = lst_filme[i].sinopse
        imageViewHolder.img!!.setImageResource(R.drawable.vingadores)

    }

    override fun getItemCount(): Int {
        return lst_filme.size
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var img: ImageView? = null
        var lbl_titulo: TextView? = null
        var lbl_horarios: TextView? = null
        var lbl_sinopse: TextView? = null

        init {
            img = itemView.findViewById(R.id.img_lista_sessao)
            lbl_titulo = itemView.findViewById(R.id.lbl_lista_filme_titulo)
            lbl_horarios = itemView.findViewById(R.id.lbl_lista_sessao_sala)
            lbl_sinopse = itemView.findViewById(R.id.lbl_lista_filme_sinopse)
        }

    }

}
