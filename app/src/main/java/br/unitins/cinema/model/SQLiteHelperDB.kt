package br.unitins.cinema.model

import android.content.Context
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

    fun cadastraUsuario(usuario: Usuario) {

        val insert_usuario = "INSERT INTO usuario (nome, senha, email) VALUES ('" +
                usuario.nome + "', '" +
                usuario.senha + "', '" +
                usuario.email + "');"

        return this.database!!.execSQL(insert_usuario)

    }

    fun getUsuario(email: String, senha: String): Usuario{

        val select_usuario = "SELECT * FROM usuario WHERE email = '" + email + "' and senha = '" + senha +"';"

        val cursor = this.database!!.rawQuery(select_usuario, null);

        if(cursor.count > 0){
            cursor.moveToFirst();

            return Usuario(
                cursor.getInt(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("nome")),
                cursor.getString(cursor.getColumnIndex("email")),
                cursor.getString(cursor.getColumnIndex("senha"))
            )

        }

        return Usuario(0, "null", "", "");

    }

    fun adicionaFilme(filme: Filme) {

        val insert_filme = "INSERT INTO filme (titulo, sinopse, duracao, genero, imagem, dtEstreia, dtFim) values ('" +
                filme.titulo + "', '" +
                filme.sinopse + "', '" +
                filme.duracao + "', '" +
                filme.genero + "', '" +
                filme.imagem + "', " +
                filme.programacao.dtEstreia.time + ", " +
                filme.programacao.dtFim.time + ");"

        this.database!!.execSQL(insert_filme)

        val id_cursor = this.database!!.rawQuery("SELECT id FROM filme ORDER BY id DESC LIMIT 1;", null)

        var id = -1
        if (id_cursor.moveToFirst()) {
            id = id_cursor.getInt(id_cursor.getColumnIndex("id"))
        }

        if (id != -1) {
            val finalId = id
            filme.programacao.lstHorarios.forEach { horario ->
                this.database!!.execSQL(
                    "INSERT INTO horarios_filme(horario, id_filme) VALUES(" +
                            horario.hrInicio.time + ", " + finalId + ");"
                )
            }
        }

    }

    fun getFilmes(): ArrayList<Filme> {

        val mock_lista_filmes = ArrayList<Filme>();

        val cursor = this.database!!.rawQuery("SELECT * FROM filme;", null);

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val imagem = cursor.getString(cursor.getColumnIndex("imagem"))
                val sinopse = cursor.getString(cursor.getColumnIndex("sinopse"))
                val titulo = cursor.getString(cursor.getColumnIndex("titulo"))
                val duracao = cursor.getInt(cursor.getColumnIndex("duracao"))
                val genero = cursor.getString(cursor.getColumnIndex("genero"))
                val dtEstreia = cursor.getLong(cursor.getColumnIndex("dtEstreia"))
                val dtFim = cursor.getLong(cursor.getColumnIndex("dtFim"))

                val mock_lista_horarios = ArrayList<Horario>();

                val cursor_h =
                    this.database!!.rawQuery("SELECT * FROM horarios_filme WHERE id_filme = " + id + ";", null);
                if (cursor_h.moveToFirst()) {
                    while (cursor_h.moveToNext()) {

                        val horario = Horario(Date(cursor_h.getLong(cursor_h.getColumnIndex("horario"))));
                        mock_lista_horarios.add(horario);

                    }
                }

                val programacao = Programacao(Date(dtEstreia), Date(dtFim), mock_lista_horarios);

                val filme = Filme(id, titulo, duracao, genero, imagem, sinopse, programacao);

                mock_lista_filmes.add(filme);

            } while (cursor.moveToNext());

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
                    "titulo TEXT, duracao INTEGER, genero TEXT," +
                    " sinopse TEXT, imagem TEXT," +
                    " dtEstreia INTEGER, dtFim INTEGER);"
        )

        //cria tabela horarios dos filmes
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS horarios_filme(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "horario INTEGER, id_filme INTEGER NOT NULL," +
                    " FOREIGN KEY(id_filme) REFERENCES filme(id));"
        )

        db.execSQL(
            "CREATE TABLE IF NOT EXISTS usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "nome TEXT, email TEXT, senha TEXT" +
                    ");"
        )

        db.execSQL("INSERT INTO usuario (email, senha, nome) VALUES ('admin', 'admin', 'admin');")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private val NOME_BANCO = "cinema_database.db"
        private val VERSAO = 1
    }
}
