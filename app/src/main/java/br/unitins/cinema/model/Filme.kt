package br.unitins.cinema.model

class Filme constructor(var id: Int,
                        var titulo: String,
                        var duracao: Int,
                        var genero: String,
                        var imagem: ByteArray,
                        var sinopse: String){

    override fun toString(): String {
        return this.titulo
    }
}