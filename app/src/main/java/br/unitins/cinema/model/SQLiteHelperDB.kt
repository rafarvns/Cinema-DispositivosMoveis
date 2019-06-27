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
        val insert_usuario = "INSERT INTO usuario (nome, senha, email, admin) VALUES ('" +
                usuario.nome + "', '" +
                usuario.senha + "', '" +
                usuario.email + "', '" +
                usuario.admin + "');"
        return this.database!!.execSQL(insert_usuario)
    }

    fun getUsuario(email: String, senha: String): Usuario{
        val select_usuario = "SELECT * FROM usuario WHERE email = '" + email + "' and senha = '" + senha +"';"
        val cursor = this.database!!.rawQuery(select_usuario, null)
        if(cursor.count > 0){
            cursor.moveToFirst()
            return Usuario(
                cursor.getInt(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("nome")),
                cursor.getString(cursor.getColumnIndex("email")),
                cursor.getString(cursor.getColumnIndex("senha")),
                cursor.getString(cursor.getColumnIndex("admin"))
            )
        }
        return Usuario(0, "null", "", "", "")
    }

    fun adicionaFilme(filme: Filme) {
        val insert_filme = "INSERT INTO filme (titulo, sinopse, duracao, genero, imagem) values ('" +
                                        filme.titulo + "', '" +
                                        filme.sinopse + "', '" +
                                        filme.duracao + "', '" +
                                        filme.genero + "', '" +
                                        filme.imagem + "');"
        this.database!!.execSQL(insert_filme)
    }

    fun getFilmes(): ArrayList<Filme> {

        val mock_lista_filmes = ArrayList<Filme>()

        val cursor = this.database!!.rawQuery("SELECT * FROM filme;", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val imagem = cursor.getBlob(cursor.getColumnIndex("imagem"))
                val sinopse = cursor.getString(cursor.getColumnIndex("sinopse"))
                val titulo = cursor.getString(cursor.getColumnIndex("titulo"))
                val duracao = cursor.getInt(cursor.getColumnIndex("duracao"))
                val genero = cursor.getString(cursor.getColumnIndex("genero"))

                val mock_lista_sessao = ArrayList<Sessao>()

                val cursor_s = this.database!!
                                    .rawQuery("SELECT * FROM sessao, sala WHERE id_filme = " + id + " and id_sala = sala.id;", null)
                if (cursor_s.moveToFirst()) {
                    while (cursor_s.moveToNext()) {

                        val idSessao = cursor_s.getInt(cursor_s.getColumnIndex("id"))
                        val dia = cursor_s.getInt(cursor_s.getColumnIndex("dia"))
                        val hora = cursor_s.getInt(cursor_s.getColumnIndex("hora"))
                        val idSala = cursor_s.getInt(cursor_s.getColumnIndex("sala.id"))
                        val poltronas = cursor_s.getInt(cursor_s.getColumnIndex("poltronas"))
                        val sala = Sala(idSala, poltronas)
                        val sessao = Sessao(idSessao, dia, hora, sala)

                        mock_lista_sessao.add(sessao)
                    }
                }

                val filme = Filme(id, titulo, duracao, genero, imagem, sinopse, mock_lista_sessao)

                mock_lista_filmes.add(filme)

            } while (cursor.moveToNext())

        }

        return mock_lista_filmes

    }

    fun closeDatabase() {
        this.database!!.close()
        this.database = null
        this.close()
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(
            "CREATE TABLE IF NOT EXISTS filme (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "titulo TEXT, duracao INTEGER, genero TEXT," +
                    " sinopse TEXT, imagem BLOB);"
        )

        db.execSQL(
            "CREATE TABLE IF NOT EXISTS sessao (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "dia INTEGER, hora INTEGER, id_filme INTEGER, id_sala INTEGER, " +
                    "FOREIGN KEY(id_filme) REFERENCES filme(id), FOREIGN KEY(id_sala) REFERENCES sala(id));"
        )

        db.execSQL(
            "CREATE TABLE IF NOT EXISTS sala (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "poltronas INTEGER);"
        )

        db.execSQL(
            "CREATE TABLE IF NOT EXISTS usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "nome TEXT, email TEXT, senha TEXT, admin TEXT);"
        )

        db.execSQL("INSERT INTO usuario (email, senha, nome, admin) VALUES ('admin', 'admin', 'admin', 'true');")
        db.execSQL("INSERT INTO usuario (email, senha, nome, admin) VALUES ('', '', '', 'true');")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private val NOME_BANCO = "cinema_database.db"
        private val VERSAO = 1
    }
}
