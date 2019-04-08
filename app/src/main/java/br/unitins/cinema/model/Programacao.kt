package br.unitins.cinema.model

import java.util.Date

class Programacao constructor(var dtEstreia: Date, var dtFim: Date, var lstHorarios: ArrayList<Horario>){

    fun getHorarios(): String {
        var str: String = ""
        lstHorarios.forEach(){
            str += "14:00 | "
        }
        return str
    }

}