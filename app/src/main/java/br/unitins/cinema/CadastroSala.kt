package br.unitins.cinema

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.unitins.cinema.model.SQLiteHelperDB
import br.unitins.cinema.model.Sala
import kotlinx.android.synthetic.main.activity_cadastro_sala.*
import kotlinx.android.synthetic.main.activity_cadastro_sessao.*

class CadastroSala : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_sala)


        bt_Cadastrar_sala.setOnClickListener{

            val numero = edtnSala!!.text.toString()
            val poltronas = edtqPoltrona!!.text.toString()

            val sala = Sala(0, numero.toInt(), poltronas.toInt())
            val database = SQLiteHelperDB(this)
            database.cadastraSala(sala)

            Toast.makeText(this, "A sala foi cadastrada!", Toast.LENGTH_SHORT).show()

            finish()

        }

        bt_Cancelar_cad_sala.setOnClickListener{
            finish()
        }

    }
}
