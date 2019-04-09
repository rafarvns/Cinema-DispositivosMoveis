package br.unitins.cinema

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro_usuario.*

class CadastroUsuario : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuario)


        var nome: String
        var email: String
        var senha: String
        var confirma_senha: String
        var mensagem: String
        mensagem = ""
        nome = txt_nome.toString()
        email = txt_email.toString()
        senha = txt_senha.toString()
        confirma_senha = txt_confirm_senha.toString()

        var Funcionario: Dados_Pessoa
        Funcionario = Dados_Pessoa()
        Funcionario.adiciona_administrador()

        bt_cadastrar.setOnClickListener(){
            mensagem = Funcionario.verifica_email(nome, email, senha, confirma_senha)
        }
        if (mensagem.equals(" ")){
            print(mensagem)
        }else{
            txt_msg.setText(mensagem)
        }
    }

}