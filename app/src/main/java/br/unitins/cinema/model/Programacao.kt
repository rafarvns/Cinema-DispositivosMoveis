package br.unitins.cinema.model

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar
import kotlin.collections.ArrayList


class Programacao constructor(var dtEstreia: Date, var dtFim: Date, var lstHorarios: ArrayList<Horario>){

    fun getHorarios(): String {
        var str: String = ""
        lstHorarios.forEach(){ horario ->

            str +=  "16:00 | ";

        }
        return str
    }


}


