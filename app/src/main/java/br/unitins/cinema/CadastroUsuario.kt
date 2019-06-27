package br.unitins.cinema

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import br.unitins.cinema.model.SQLiteHelperDB
import br.unitins.cinema.model.Usuario

import kotlinx.android.synthetic.main.activity_cadastro_usuario.*

class CadastroUsuario : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuario)

        bt_cadastrar.setOnClickListener{

            val nome = txt_nome_cad.text.toString()
            val email = txt_email_cad.text.toString()
            val senha = txt_senha_cad.text.toString()
            val confirmaSenha = txt_confirm_senha_cad.text.toString()
            val isAdmin = "true"

            if(senha.equals(confirmaSenha)){
                val db = SQLiteHelperDB(this)
                db.cadastraUsuario(Usuario(0, nome, email, senha, isAdmin))
                Toast.makeText(this, "Cadastro Realizado!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Perfil::class.java))
                finish()
            } else {
                Toast.makeText(this, "As senhas não conferem!", Toast.LENGTH_SHORT).show()
            }

        }

        bt_Cancelar_Cadastro.setOnClickListener{
            finish()
        }
    }
}