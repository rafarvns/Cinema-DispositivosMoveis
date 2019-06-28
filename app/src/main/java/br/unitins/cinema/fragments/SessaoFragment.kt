package br.unitins.cinema.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.unitins.cinema.R
import br.unitins.cinema.adapters.AdapterListaCrudFilme
import br.unitins.cinema.adapters.AdapterListaCrudSessao
import br.unitins.cinema.model.SQLiteHelperDB

class SessaoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sessao, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_lista_crud_sessao)
        val db = SQLiteHelperDB(view.context)
        val layoutManager = GridLayoutManager(view.context, 1)
        recyclerView!!.layoutManager = layoutManager
        recyclerView.hasFixedSize()
        val adapterSessao = AdapterListaCrudSessao(db.getsessoes(), view.context)
        recyclerView.adapter = adapterSessao

    }

}
