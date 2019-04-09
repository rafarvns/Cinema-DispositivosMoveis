package br.unitins.cinema

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login_usuario.*


class Entrar_Usuario: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_usuario)
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
            if (check_administrador.isChecked == true){
                dados_Pessoa.verifica_Email_administrador(email, senha)
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

    }
}