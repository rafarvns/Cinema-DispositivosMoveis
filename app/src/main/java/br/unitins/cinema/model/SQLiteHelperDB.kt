package br.unitins.cinema.model

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.Date

class SQLiteHelperDB(context: Context) : SQLiteOpenHelper(context, NOME_BANCO, null, VERSAO) {

    private var context: Context? = null
    private var database: SQLiteDatabase? = null

    init {
        this.context = context
        this.database = this.writableDatabase
    }

    fun adicionaFilme(filme: Filme) {

        val insert_filme = "INSERT INTO filme (titulo, sinopse, imagem, dtEstreia, dtFim) values ('" +
                filme.titulo + "', '" +
                filme.sinopse + "', '" +
                filme.imagem + "', " +
                filme.programacao.dtEstreia.time + ", " +
                filme.programacao.dtFim.time + ");"

        this.database!!.execSQL(insert_filme)

        val id_cursor = this.database!!.rawQuery("SELECT id FROM filme ORDER BY id DESC LIMIT 1;", null)

        var id = -1
        if (id_cursor.moveToFirst()) {
            id = id_cursor.getInt(id_cursor.getColumnIndex("id"))
        }

        if(id != -1) {
            val finalId = id
            filme.programacao.lstHorarios.forEach { horario ->
                this.database!!.execSQL(
                    "INSERT INTO horarios_filme(horario, id_filme) VALUES(" +
                            horario.hrInicio.time + ", " + finalId + ");"
                )
            }
        }

    }

    fun getFilmes(): ArrayList<Filme>{

        val mock_lista_filmes = ArrayList<Filme>();

        val cursor = this.database!!.rawQuery("SELECT * FROM filme;", null);

        if(cursor.moveToFirst()){
            do{
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val imagem = cursor.getString(cursor.getColumnIndex("imagem"))
                val sinopse = cursor.getString(cursor.getColumnIndex("sinopse"))
                val titulo = cursor.getString(cursor.getColumnIndex("titulo"))
                val dtEstreia = cursor.getLong(cursor.getColumnIndex("dtEstreia"))
                val dtFim = cursor.getLong(cursor.getColumnIndex("dtFim"))

                val mock_lista_horarios = ArrayList<Horario>();

                val cursor_h = this.database!!.rawQuery("SELECT * FROM horarios_filme WHERE id_filme = " + id + ";", null);
                if(cursor_h.moveToFirst()) {
                    while (cursor_h.moveToNext()) {

                        val horario = Horario(Date(cursor_h.getLong(cursor_h.getColumnIndex("horario"))));
                        mock_lista_horarios.add(horario);

                    }
                }

                val programacao = Programacao(Date(dtEstreia), Date(dtFim), mock_lista_horarios);

                val filme = Filme(id, titulo, imagem, sinopse, programacao);

                mock_lista_filmes.add(filme);

            }while(cursor.moveToNext());

        }

        return mock_lista_filmes;

    }

    fun closeDatabase() {
        this.database!!.close()
        this.database = null
        this.close()
    }

    override fun onCreate(db: SQLiteDatabase) {

        //cria tabela filme
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS filme (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "titulo TEXT, sinopse TEXT, imagem TEXT," +
                    " dtEstreia INTEGER, dtFim INTEGER);"
        )

        //cria tabela horarios dos filmes
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS horarios_filme(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "horario INTEGER, id_filme INTEGER NOT NULL," +
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
