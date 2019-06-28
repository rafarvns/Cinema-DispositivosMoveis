package br.unitins.cinema.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
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

    fun cadastraSala(sala : Sala){
        val insert_sala = "INSERT INTO sala (poltronas, numero) VALUES ('"+sala.poltronas+"', '"+sala.numero+"');"
        this.database!!.execSQL(insert_sala)
    }

    fun getSalas(): ArrayList<Sala>{
        val lst_sala = ArrayList<Sala>()
        val select_sala = "SELECT * FROM sala;"
        val cursor = this.database!!.rawQuery(select_sala, null)
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val poltronas = cursor.getInt(cursor.getColumnIndex("poltronas"))
                val numero = cursor.getInt(cursor.getColumnIndex("numero"))
                val sala = Sala(id, numero, poltronas)
                lst_sala.add(sala)
            }
        }
        Log.i("asdf", "" + lst_sala.count())
        return lst_sala
    }

    fun cadastraSessao(sessao : Sessao){
        val insert_sessao = "INSERT INTO sessao (dia, hora, id_filme, id_sala) VALUES ("+sessao.dia+", "+sessao.hora+", "+sessao.filme.id+", "+sessao.sala.id+");"
        return this.database!!.execSQL(insert_sessao)
    }

    fun getsessoes(): ArrayList<Sessao>{
        val lst_sessao = ArrayList<Sessao>()
        val select_sessao = "SELECT * FROM sessao;"
        val cursor = this.database!!.rawQuery(select_sessao, null)
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val dia = cursor.getInt(cursor.getColumnIndex("dia"))
                val hora = cursor.getInt(cursor.getColumnIndex("hora"))
                val idSala = cursor.getInt(cursor.getColumnIndex("id_sala"))
                val idFilme = cursor.getInt(cursor.getColumnIndex("id_filme"))

                val cursor_s = this.database!!.rawQuery("SELECT * FROM sala WHERE id = "+idSala+";", null)
                val sala = Sala(0, 0,0)
                if(cursor_s.count > 0){
                    cursor_s.moveToFirst()
                    sala.id = cursor_s.getInt(cursor_s.getColumnIndex("id"))
                    sala.numero = cursor_s.getInt(cursor_s.getColumnIndex("numero"))
                    sala.poltronas = cursor_s.getInt(cursor_s.getColumnIndex("poltronas"))
                }

                val cursor_f = this.database!!.rawQuery("SELECT * FROM filme WHERE id = "+idFilme+";", null)
                val filme = Filme(0, "",0, "", ByteArray(0), "")
                if(cursor_f.count > 0){
                    cursor_f.moveToFirst()
                    filme.id = cursor_f.getInt(cursor_f.getColumnIndexOrThrow("id"))
//                    filme.imagem = cursor.getBlob(cursor.getColumnIndex("imagem"))
                    filme.sinopse = cursor_f.getString(cursor_f.getColumnIndex("sinopse"))
                    filme.titulo = cursor_f.getString(cursor_f.getColumnIndex("titulo"))
                    filme.duracao = cursor_f.getInt(cursor_f.getColumnIndex("duracao"))
                    filme.genero = cursor_f.getString(cursor_f.getColumnIndex("genero"))
                }

                val sessao = Sessao(id, dia, hora, filme, sala)
                lst_sessao.add(sessao)
            }
        }
        return lst_sessao
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

    fun deletaFilme(id_filme: Int){
        val delete_filme = "DELETE FROM filme WHERE id = "+id_filme+";"
        val cursor_f = this.database!!.rawQuery("SELECT * FROM sessao WHERE id_filme = "+id_filme+";", null)
        if(cursor_f.count > 0){

            Toast.makeText(this.context, "Este filme possúi sessões vinculadas!", Toast.LENGTH_SHORT).show()

        } else {
            this.database!!.execSQL(delete_filme)
        }
    }

    fun deletaSala(id_sala: Int){
        val delete_sala = "DELETE FROM sala WHERE id = "+id_sala+";"
        val cursor_f = this.database!!.rawQuery("SELECT * FROM sessao WHERE id_sala = "+id_sala+";", null)
        if(cursor_f.count > 0){

            Toast.makeText(this.context, "Esta sala possúi sessões vinculadas!", Toast.LENGTH_SHORT).show()

        } else {
            this.database!!.execSQL(delete_sala)
        }
    }

    fun deleteSessao(id_sessao: Int){
        val delete_sessao = "DELETE FROM sessao WHERE id = "+id_sessao+";"
        this.database!!.execSQL(delete_sessao)
    }

    fun deleteUsuario(id_usuario: Int){
        val delete_usuario = "DELETE FROM usuario WHERE id = "+id_usuario+";"
        this.database!!.execSQL(delete_usuario)
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

                val filme = Filme(id, titulo, duracao, genero, imagem, sinopse)

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
            "CREATE TABLE IF NOT EXISTS sala (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "poltronas INTEGER, numero INTEGER);"
        )

        db.execSQL(
            "CREATE TABLE IF NOT EXISTS sessao (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "dia INTEGER, hora INTEGER, id_filme INTEGER, id_sala INTEGER, " +
                    "FOREIGN KEY(id_filme) REFERENCES filme(id), FOREIGN KEY(id_sala) REFERENCES sala(id));"
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
