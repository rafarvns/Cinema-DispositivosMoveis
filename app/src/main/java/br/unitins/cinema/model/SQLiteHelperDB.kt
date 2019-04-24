package br.unitins.cinema.model

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelperDB(context: Context) : SQLiteOpenHelper(context, NOME_BANCO, null, VERSAO) {

    private var context: Context? = null
    private var database: SQLiteDatabase? = null

    init {
        this.context = context
        this.database = this.writableDatabase
    }

    fun adicionaFilme(filme: Filme) {

        val insert_filme = "INSERT INTO filme (titulo, sinopse, imagem, dtEstreia, dtFim) values (" +
                filme.titulo + ", " +
                filme.sinopse + ", " +
                filme.imagem + ", " +
                filme.programacao.dtEstreia + ", " +
                filme.programacao.dtFim + ");"

        this.database!!.execSQL(insert_filme)

        val id_cursor = this.database!!.rawQuery("SELECT id FROM filme ORDER BY id DESC LIMIT 1;", null)

        var id = -1
        if (id_cursor.moveToFirst()) {
            id = id_cursor.getInt(id_cursor.getColumnIndex("id"))
        }

        val finalId = id
        filme.programacao.lstHorarios.forEach { horario ->
            this.database!!.execSQL(
                "INSERT INTO horarios_filme(horario, id_filme) VALUES(" +
                        "" + horario.hrInicio + ", " + finalId + ");"
            )
        }


    }

    fun closeDatabase() {
        this.database!!.close()
        this.database = null
        this.close()
    }

    override fun onCreate(db: SQLiteDatabase) {

        //cria tabela filme
        this.database!!.execSQL(
            "CREATE TABLE IF NOT EXISTS filme (id INT PRIMARY KEY AUTOINCREMENT, " +
                    "titulo TEXT, sinopse TEXT, imagen TEXT," +
                    " dtEstreia Date, dtFim Date);"
        )

        //cria tabela horarios dos filmes
        this.database!!.execSQL(
            "CREATE TABLE IF NOT EXISTS horarios_filme(id INT PRIMARY KEY AUTOINCREMENT, " +
                    "horario Date, id_filme INT NOT NULL," +
                    " FOREIGN KEY(id_filme) REFERENCES filme(id));"
        )

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private val NOME_BANCO = "cinema_database.db"
        private val VERSAO = 1
    }
}
