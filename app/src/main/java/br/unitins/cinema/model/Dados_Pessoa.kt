package br.unitins.cinema.model

class Dados_Pessoa() {
    var funcionario: ArrayList<Pessoa_Array> = arrayListOf()
    var administrador: ArrayList<Pessoa_Array> = arrayListOf()
    var mensagem: String = ""
    fun verifica_email(nome: String, email: String, senha: String,
                       confirma_senha: String): String{
        var cont_init: Boolean
        cont_init = true
        while (cont_init){
            //confere se o email inserido já está no array
            if (funcionario.equals(email)) {
                //imprime uma mensagem de advertencia
                mensagem = "O endereço de e-mail inserido já se encontra em uso"
                cont_init = false
            }else{
                //verifica a senha inserida
                mensagem = verifica_senha(senha, confirma_senha)
                if (mensagem == "sucesso"){
                    var funcionario: Pessoa_Array
                    funcionario = Pessoa_Array(nome, email, senha)
                    //chama a função de inserção no array do funcionario
                    cadastra_funcionario(funcionario)
                }
                cont_init = false
            }
        }
        return mensagem
    }
    fun verifica_email(email: String, senha: String): String{
        var cont_init: Boolean
        cont_init = true
        while (cont_init){
            //confere se o email inserido já está no array
            if (funcionario.equals(email)) {
                mensagem = verifica_senha(senha)
                cont_init = false
            }
        }
        return mensagem
    }

    fun verifica_senha(senha: String, confirma_senha: String): String{
        if (senha == confirma_senha){
            mensagem = "sucesso"
        }else{
            mensagem = "Senhas incoerentes"
        }
        return mensagem
    }

    fun verifica_senha(senha: String): String{
        if (funcionario.equals(senha)){
            mensagem = "sucesso"
        }else{
            mensagem = "Senha incorreta"
        }
        return mensagem
    }

    fun cadastra_funcionario(funcionario: Pessoa_Array){
        this.funcionario.add(funcionario)
    }
    fun cadastra_administrador(){
        var administrador: Pessoa_Array
        administrador = Pessoa_Array("Julieta Chaves", "paz.julia7@gmail.com", "12345")
        this.administrador.add(administrador)
    }
    fun verifica_Email_administrador(email: String, senha: String):Boolean{
        var mensagem: Boolean = false
        if (administrador.equals(email) && administrador.equals(senha)) {
            mensagem = true
        }
        return mensagem
    }

}