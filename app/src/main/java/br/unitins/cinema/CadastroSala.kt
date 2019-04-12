package br.unitins.cinema

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CadastroSala : Activity() {

    private var edtnSala: EditText? = null
    private var edtqPoltrona: EditText? = null
    private var bcadSala: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        edtnSala = findViewById(R.id.edtnSala)
        edtqPoltrona = findViewById(R.id.edtqPoltrona)
        bcadSala = findViewById(R.id.bcadSala)

        if (edtnSala!!.text.toString() != "") {
            if (edtqPoltrona!!.text.toString() != "") {

                bcadSala!!.setOnClickListener { }

            } else
                Toast.makeText(this, "Preencha o campo da Poltrona", Toast.LENGTH_SHORT)
        } else
            Toast.makeText(this, "Preencha o campo da sala", Toast.LENGTH_SHORT)

    }
}
