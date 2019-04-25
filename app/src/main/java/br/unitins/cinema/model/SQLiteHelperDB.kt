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

        val insert_filme = "INSERT INTO filme (titulo, sinopse, imagen, dtEstreia, dtFim) values ('" +
                filme.titulo + "', '" +
                filme.sinopse + "', '" +
                filme.imagem + "', '" +
                filme.programacao.dtEstreia.toString() + "', '" +
                filme.programacao.dtFim.toString() + "');"

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
                    "INSERT INTO horarios_filme(horario, id_filme) VALUES('" +
                            "" + horario.hrInicio.toString() + "', " + finalId + ");"
                )
            }
        }

    }

    fun getFilmes(): ArrayList<Filme>{

        val mock_lista_filmes = ArrayList<Filme>();

        val cursor = this.database!!.rawQuery("SELECT * FROM filme;", null);

        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){

                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                val imagem = cursor.getString(cursor.getColumnIndex("imagen"));
                val sinopse = cursor.getString(cursor.getColumnIndex("sinopse"));
                val titulo = cursor.getString(cursor.getColumnIndex("titulo"));
                val dtEstreia = Date(100);
                val dtFim = Date(100);

                val mock_lista_horarios = ArrayList<Horario>();

                val cursor_h = this.database!!.rawQuery("SELECT * FROM horarios_filme WHERE id_filme = " + id + ";", null);
                if(cursor_h.moveToFirst()) {
                    while (cursor_h.moveToNext()) {

                        val horario = Horario(Date(100));
                        mock_lista_horarios.add(horario);

                    }
                }

                val programacao = Programacao(dtEstreia, dtFim, mock_lista_horarios);

                val filme = Filme(id, titulo, imagem, sinopse, programacao);

                mock_lista_filmes.add(filme);

            }

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
                    "titulo TEXT, sinopse TEXT, imagen TEXT," +
                    " dtEstreia Date, dtFim Date);"
        )

        //cria tabela horarios dos filmes
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS horarios_filme(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "horario Date, id_filme INTEGER NOT NULL," +
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
