package br.unitins.cinema

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import br.unitins.cinema.model.SQLiteHelperDB

class LoginUsuario : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_usuario)

            var email: String
            var senha: String

            var edt_email = findViewById<EditText>(R.id.txt_email_login)
            var edt_senha = findViewById<EditText>(R.id.txt_senha_login)



            var bt_entrar = findViewById<Button>(R.id.bt_entrar)
            bt_entrar.setOnClickListener{

                email = edt_email.text.toString()
                senha = edt_senha.text.toString()

                val db = SQLiteHelperDB(this)
                val usuario = db.getUsuario(email, senha)

                if(usuario.id != 0){
                    Toast.makeText(this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, Gerencia::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "E-mail ou senha inválidos!", Toast.LENGTH_SHORT).show()
                }

            }

            var bt_voltar = findViewById<Button>(R.id.bt_voltar_login_usuario)

            bt_voltar.setOnClickListener{
                startActivity(Intent(this, MainActivity::class.java))
            }


    }
}
