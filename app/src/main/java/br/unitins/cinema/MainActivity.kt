package br.unitins.cinema

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import br.unitins.cinema.adapters.AdapterListaFilme
import br.unitins.cinema.model.Filme
import br.unitins.cinema.model.Horario
import br.unitins.cinema.model.Programacao
import br.unitins.cinema.model.SQLiteHelperDB
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Date
import kotlin.collections.ArrayList
import kotlin.random.Random

class MainActivity : Activity() {

    var recyclerView: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var adapterFilme: AdapterListaFilme? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_lista_filmes)
        val db = SQLiteHelperDB(this);
        layoutManager = GridLayoutManager(this, 1)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.hasFixedSize()
        adapterFilme = AdapterListaFilme(db.getFilmes(), this)
        recyclerView!!.adapter = adapterFilme

        btLoginPrincipal.setOnClickListener {
            startActivity(Intent(this, Perfil::class.java))
        }

    }

}
















