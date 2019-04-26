package br.unitins.cinema

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_perfil.*

class Perfil : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        bt_sair.setOnClickListener {
            finish()
        }
        bt_cadastrar_usuario_perfil.setOnClickListener {
            startActivity(Intent(this, CadastroUsuario::class.java))
        }

        bt_cadastrar_filme_perfil.setOnClickListener {
            startActivity(Intent(this, CadastroFilme::class.java))
        }
    }

}
