package br.unitins.cinema

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro_sessao.*

class CadastroSala : Activity() {

    private var edtnSala: EditText? = null
    private var edtqPoltrona: EditText? = null
    private var bcadSala: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_sala)

        edtnSala = findViewById(R.id.edtnSala)
        edtqPoltrona = findViewById(R.id.edtqPoltrona)
        bcadSala = findViewById(R.id.btcadSala)

        if (edtnSala!!.text.toString() != "") {
            if (edtqPoltrona!!.text.toString() != "") {

                bcadSala!!.setOnClickListener { }

            } else
                Toast.makeText(this, "Preencha o campo da Poltrona", Toast.LENGTH_SHORT)
        } else
            Toast.makeText(this, "Preencha o campo da sala", Toast.LENGTH_SHORT)

        btn_cancelar.setOnClickListener {
            finish()
        }
    }
}
