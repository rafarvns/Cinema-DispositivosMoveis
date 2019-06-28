package br.unitins.cinema

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.unitins.cinema.adapters.AdapterListaFilme
import br.unitins.cinema.model.Filme
import br.unitins.cinema.model.Sessao
import br.unitins.cinema.model.SQLiteHelperDB
import kotlinx.android.synthetic.main.activity_cadastro_filme.*
import java.util.*
import kotlin.collections.ArrayList

class CadastroFilme : Activity() {

    private var titulo: EditText? = null
    private var duracao: EditText? = null
    private var genero: EditText? = null
    private var sinopse: EditText? = null
    private var cadastrar: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_filme)

        titulo = findViewById(R.id.titulo)
        duracao = findViewById(R.id.duracao)
        genero = findViewById(R.id.genero)
        sinopse = findViewById(R.id.sinopse)

        bt_Cancelar_Cadastro_filme.setOnClickListener{
            finish()
        }

        btCadFilme!!.setOnClickListener {
            val filme = Filme(
                1,
                titulo!!.text.toString(),
                duracao!!.text.toString().toInt(),
                genero!!.text.toString(),
                ByteArray(1),
                sinopse!!.text.toString()
            )
            val db = SQLiteHelperDB(this)
            db.adicionaFilme(filme)
            Toast.makeText(this, "O filme foi cadastrado!", Toast.LENGTH_SHORT).show()
            finish()
        }

    }




}
