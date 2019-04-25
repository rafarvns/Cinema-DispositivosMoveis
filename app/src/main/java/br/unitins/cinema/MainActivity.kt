package br.unitins.cinema

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.GridLayout
import br.unitins.cinema.adapters.AdapterListaFilme
import br.unitins.cinema.model.Filme
import br.unitins.cinema.model.Horario
import br.unitins.cinema.model.Programacao
import br.unitins.cinema.model.SQLiteHelperDB
import java.util.Date
import kotlin.random.Random

class MainActivity : Activity() {

    var recyclerView: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var adapterFilme: AdapterListaFilme? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_lista_filmes)

        val r = Random(Math.random().toInt());

        val horario = Horario(Date(r.nextLong(99997654321)))
        val mock_list_horarios = ArrayList<Horario>()

        mock_list_horarios.add(horario)
        horario.hrInicio.time = horario.hrInicio.time + 10000
        mock_list_horarios.add(horario)
        horario.hrInicio.time = horario.hrInicio.time + 10000
        mock_list_horarios.add(horario)
        horario.hrInicio.time = horario.hrInicio.time + 10000
        mock_list_horarios.add(horario)
        horario.hrInicio.time = horario.hrInicio.time + 10000
        mock_list_horarios.add(horario)

        val mock_lista_filmes = ArrayList<Filme>()

        val db = SQLiteHelperDB(this);

        var filme = Filme(0, "Filme Teste", "img.png", "Apenas um teste mesmo", Programacao(Date(r.nextLong(999997654321)), Date(Math.random().toLong()), mock_list_horarios))
        mock_lista_filmes.add(filme)
        db.adicionaFilme(filme);
        filme = Filme(0, "Filme Teste 2", "img.png", "Apenas um teste mesmo 2", Programacao(Date(r.nextLong(99999997654321)), Date(Math.random().toLong()), mock_list_horarios))
        mock_lista_filmes.add(filme)
        db.adicionaFilme(filme);
        filme = Filme(0, "Filme Teste 3", "img.png", "Apenas um teste mesmo 3", Programacao(Date(Math.random().toLong()), Date(Math.random().toLong()), mock_list_horarios))
        mock_lista_filmes.add(filme)
        db.adicionaFilme(filme);
        filme = Filme(0, "Filme Teste 4", "img.png", "Apenas um teste mesmo 4", Programacao(Date(Math.random().toLong()), Date(Math.random().toLong()), mock_list_horarios))
        mock_lista_filmes.add(filme)
        db.adicionaFilme(filme);
        filme = Filme(0, "Filme Teste 5", "img.png", "Apenas um teste mesmo 5", Programacao(Date(Math.random().toLong()), Date(Math.random().toLong()), mock_list_horarios))
        mock_lista_filmes.add(filme)
        db.adicionaFilme(filme);
        filme = Filme(0, "Filme Teste 6", "img.png", "Apenas um teste mesmo 6", Programacao(Date(Math.random().toLong()), Date(Math.random().toLong()), mock_list_horarios))
        mock_lista_filmes.add(filme)
        db.adicionaFilme(filme);

        layoutManager = GridLayoutManager(this, 1)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.hasFixedSize()
        adapterFilme = AdapterListaFilme(db.getFilmes(), this)
        recyclerView!!.adapter = adapterFilme

    }



}
















