package br.unitins.cinema

import android.content.Intent
import android.os.Bundle

import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import br.unitins.cinema.fragments.UsuarioFragment
import br.unitins.cinema.fragments.FilmeFragment
import br.unitins.cinema.fragments.SalaFragment
import br.unitins.cinema.fragments.SessaoFragment
import kotlinx.android.synthetic.main.activity_gerencia.*

class Gerencia : FragmentActivity() {

    var transaction : FragmentTransaction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gerencia)

        txt_nome_view.setText("Filme")
        trocaFragment("Filme")

        btn_add.setOnClickListener {
            var nome: String
            nome = txt_nome_view.text.toString()
            if (nome.equals("Filme")) {
                startActivity(Intent(this, CadastroFilme::class.java))
            }else if (nome.equals("Salas")){
                startActivity(Intent(this, CadastroSala::class.java))
            }else if (nome.equals("Sessões")){
                startActivity(Intent(this, CadastroSessao::class.java))
            } else if(nome.equals("Usuários")){
                startActivity(Intent(this, CadastroUsuario::class.java))
            } else {
                startActivity(Intent(this, CadastroFilme::class.java))
            }
        }

        btn_filmes.setOnClickListener {
            txt_nome_view.setText("Filme")
            trocaFragment("Filme")
        }

        btn_salas.setOnClickListener {
            txt_nome_view.setText("Salas")
              trocaFragment("Salas")
        }

        btn_sessoes.setOnClickListener{
            txt_nome_view.setText("Sessões")
            trocaFragment("Sessões")
        }
        btn_conta.setOnClickListener{
            txt_nome_view.setText("Usuários")
            trocaFragment("Usuários")
        }
    }

    fun trocaFragment(tela : String){

        if (tela.equals("Filme")) {
            val filme_fragment = FilmeFragment()
            this.transaction = supportFragmentManager.beginTransaction()
            this.transaction!!.replace(R.id.fragment_gerencia, filme_fragment)
            this.transaction!!.commit()
        }else if (tela.equals("Salas")){
            val sala_fragment = SalaFragment()
            this.transaction = supportFragmentManager.beginTransaction()
            this.transaction!!.replace(R.id.fragment_gerencia, sala_fragment)
            this.transaction!!.commit()
        }else if (tela.equals("Sessões")){
            val sessao_fragment = SessaoFragment()
            this.transaction = supportFragmentManager.beginTransaction()
            this.transaction!!.replace(R.id.fragment_gerencia, sessao_fragment)
            this.transaction!!.commit()
        } else if(tela.equals("Usuários")){
            val usuario_fragment = UsuarioFragment()
            this.transaction = supportFragmentManager.beginTransaction()
            this.transaction!!.replace(R.id.fragment_gerencia, usuario_fragment)
            this.transaction!!.commit()
        } else {
            val filme_fragment = FilmeFragment()
            this.transaction = supportFragmentManager.beginTransaction()
            this.transaction!!.replace(R.id.fragment_gerencia, filme_fragment)
            this.transaction!!.commit()
        }

    }

    override fun onResume() {
        super.onResume()

        trocaFragment(txt_nome_view.text.toString())
    }
}
