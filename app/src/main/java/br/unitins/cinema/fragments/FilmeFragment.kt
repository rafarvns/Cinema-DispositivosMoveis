package br.unitins.cinema.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.unitins.cinema.R
import br.unitins.cinema.adapters.AdapterListaCrudFilme
import br.unitins.cinema.adapters.AdapterListaFilme
import br.unitins.cinema.model.SQLiteHelperDB
import kotlinx.android.synthetic.main.fragment_filme.*

class FilmeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filme, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_frag_filmes)
        val db = SQLiteHelperDB(view.context)
        val layoutManager = GridLayoutManager(view.context, 1)
        recyclerView!!.layoutManager = layoutManager
        recyclerView.hasFixedSize()
        val adapterFilme = AdapterListaCrudFilme(db.getFilmes(), view.context)
        recyclerView.adapter = adapterFilme

    }


}
