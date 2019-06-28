package br.unitins.cinema

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import br.unitins.cinema.model.Filme
import br.unitins.cinema.model.SQLiteHelperDB
import br.unitins.cinema.model.Sala
import br.unitins.cinema.model.Sessao
import kotlinx.android.synthetic.main.activity_cadastro_sessao.*

class CadastroSessao : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_sessao)

        val db = SQLiteHelperDB(this)
        val filmes = db.getFilmes()
        val salas = db.getSalas()

        val spnFilmes = ArrayAdapter<Filme>(this, R.layout.support_simple_spinner_dropdown_item, filmes)
        spnFilmes.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spncadSessaoFilme.adapter = spnFilmes

        val spnSalas = ArrayAdapter<Sala>(this, R.layout.support_simple_spinner_dropdown_item, salas)
        spnSalas.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spncadSessaoSala.adapter = spnSalas

        bt_Cadastrar_sessao.setOnClickListener{

            val spnSala =  spncadSessaoSala
            val spnFilme = spncadSessaoFilme
            val data = edtcadSessaoData!!.text.toString()
            val hora = edtcadSessaoHora!!.text.toString()
            val sala = spnSala.selectedItem as Sala
            val filme = spnFilme.selectedItem as Filme

            val sessao = Sessao(0, data.toInt(), hora.toInt(), filme, sala)
            val database = SQLiteHelperDB(this)
            database.cadastraSessao(sessao)

            Toast.makeText(this, "A sessão foi cadastrada!", Toast.LENGTH_SHORT).show()
            finish()
        }

        bt_Cancelar_cad_sessao.setOnClickListener{
            finish()
        }

    }

}
