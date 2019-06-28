package br.unitins.cinema.model

class Sala constructor(var id: Int,
                       var numero: Int,
                       var poltronas: Int){

    override fun toString(): String {
        return this.numero.toString()
    }
}