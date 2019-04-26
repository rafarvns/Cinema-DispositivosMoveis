package br.unitins.cinema

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import br.unitins.cinema.model.Filme
import br.unitins.cinema.model.Horario
import br.unitins.cinema.model.Programacao
import br.unitins.cinema.model.SQLiteHelperDB
import java.util.*
import kotlin.collections.ArrayList

class CadastroFilme : Activity() {

    private var titulo: EditText? = null
    private var duracao: EditText? = null
    private var genero: EditText? = null
    //    private var classEtaria : EditText? = null
    private var sinopse: EditText? = null
    private var cadastrar: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_filme)

        titulo = findViewById(R.id.titulo)
        duracao = findViewById(R.id.duracao)
        genero = findViewById(R.id.genero)
        sinopse = findViewById(R.id.sinopse)
        cadastrar = findViewById(R.id.btCadFilme)
        var cancelar = findViewById<Button>(R.id.bt_Cancelar_Cadastro)

        cancelar.setOnClickListener{
            finish()
        }



        cadastrar!!.setOnClickListener {
            val filme = Filme(
                1,
                titulo!!.text.toString(),
                duracao!!.text.toString().toInt(),
                genero!!.text.toString(),
                "",
                sinopse!!.text.toString(),
                Programacao(Date(), Date(), ArrayList<Horario>())
            );
            val db = SQLiteHelperDB(this)
            db.adicionaFilme(filme)
            startActivity(Intent(this, MainActivity::class.java))
        }


    }




}
