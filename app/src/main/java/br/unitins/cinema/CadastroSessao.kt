package br.unitins.cinema

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class CadastroSessao : AppCompatActivity() {

    private var edtcadSessaoSala: EditText? = null
    private var edtcadSessaoFilme: EditText? = null
    private var edtcadSessaoData: EditText? = null
    private var edtcadSessaoHora: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_sessao)

        edtcadSessaoSala = findViewById(R.id.edtcadSessaoSala)
        edtcadSessaoFilme = findViewById(R.id.edtcadSessaoFilme)
        edtcadSessaoData = findViewById(R.id.edtcadSessaoData)
        edtcadSessaoHora = findViewById(R.id.edtcadSessaoHora)
    }
}
