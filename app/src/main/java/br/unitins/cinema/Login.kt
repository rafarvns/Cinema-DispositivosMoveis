package br.unitins.cinema

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import br.unitins.cinema.model.Dados_Pessoa
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_perfil.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        check_administrador.isChecked = false

        var dados_Pessoa: Dados_Pessoa
        dados_Pessoa = Dados_Pessoa()
        var email: String
        var senha: String
        var mensagem: String

        email = txt_email_login.toString()
        senha = txt_senha_login.toString()
        mensagem = dados_Pessoa.verifica_email(email, senha)

        bt_entrar.setOnClickListener() {
            check_administrador.setOnClickListener(){check_administrador.isChecked = true}
            var msg: Boolean

            if (check_administrador.isChecked == true){
                msg = dados_Pessoa.verifica_Email_administrador(email, senha)
                if (msg){
                    bt_entrar.setOnClickListener {
                        startActivity(Intent(this, Perfil::class.java))
                    }
                    val constraint_perfil =
                        findViewById<ConstraintLayout>(R.id.constraint_perfil)
                    constraint_perfil.removeView(bt_cadastrar_usuario_perfil)
                }
                check_administrador.isChecked = false

            }else{
                mensagem = "Talvez você não seja um administrador," +
                        " desmarque a caixinha e tente novamente"
                txt_email_login.setText(mensagem)
            }
            if (mensagem.equals("sucesso")) {
                //implementar pagina pós login "perfil de usuário"

            } else {
                mensagem = "Email ou senha inválidos"
                txt_email_login.setText(mensagem)
            }

        }

        bt_voltar_login_usuario.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}
