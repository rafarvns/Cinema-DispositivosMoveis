package br.unitins.cinema

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import br.unitins.cinema.model.Dados_Pessoa
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
        Funcionario.cadastra_administrador()

        bt_cadastrar.setOnClickListener(){
            //verifica se o usuário existe por meio do email inserido
            mensagem = Funcionario.verifica_email(nome, email, senha, confirma_senha)
        }
        if (mensagem.equals("sucesso")){
            txt_msg.setText("Usuário inserido com sucesso!")
            txt_nome.setText("")
            txt_email.setText("")
            txt_senha.setText("")
            txt_confirm_senha.setText("")
        }else {
            txt_msg.setText(mensagem)
        }
        bt_Cancelar_Cadastro.setOnClickListener{
            startActivity(Intent(this, Perfil::class.java))
        }
    }
}