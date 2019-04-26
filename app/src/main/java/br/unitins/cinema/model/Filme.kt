package br.unitins.cinema.model

import java.time.Duration

class Filme constructor(var id: Int, var titulo: String, var duracao: Int, var genero: String, var imagem: String,
                            var sinopse: String, var programacao: Programacao) {

}