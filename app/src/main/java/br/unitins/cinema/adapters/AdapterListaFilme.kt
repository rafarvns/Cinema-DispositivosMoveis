package br.unitins.cinema.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import br.unitins.cinema.model.Filme

import java.util.ArrayList

class AdapterListaFilme(val lst_filme: ArrayList<Filme>) : RecyclerView.Adapter<AdapterListaFilme.ImageViewHolder>() {



    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ImageViewHolder {
        return null
    }

    override fun onBindViewHolder(imageViewHolder: ImageViewHolder, i: Int) {

    }

    override fun getItemCount(): Int {
        return 0
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

}
