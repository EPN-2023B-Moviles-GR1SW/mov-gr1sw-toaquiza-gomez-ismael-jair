package com.example.examenib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import java.util.Date

class EditarResenia : AppCompatActivity() {

    var radioTrue: RadioButton? = null
    var radioFalse: RadioButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_resenia)

        val id = findViewById<EditText>(R.id.inp_editarIdResenia)
        val comentario = findViewById<EditText>(R.id.inp_editarComentario)
        val calificacion = findViewById<EditText>(R.id.inp_editarCalificacion)
        radioTrue = findViewById(R.id.rdb_recom_true_editar)
        radioFalse = findViewById(R.id.rdb_recom_false_editar)

        id.setText(BaseDatosMemoria.reseniaElegida.id.toString())
        comentario.setText(BaseDatosMemoria.reseniaElegida.comentario)
        calificacion.setText(BaseDatosMemoria.reseniaElegida.calificacion.toString())
        val recomendado: Boolean = BaseDatosMemoria.reseniaElegida.recomendado
        radioTrue?.isChecked = recomendado
        radioFalse?.isChecked = !recomendado

        val btnEditarResenia = findViewById<Button>(R.id.btn_editarResenia)
        btnEditarResenia.setOnClickListener {
            editarResenia()
            irActividad(ListViewResenias::class.java)
        }
    }

    private fun editarResenia() {
        val id = findViewById<EditText>(R.id.inp_editarIdResenia)
        val comentario = findViewById<EditText>(R.id.inp_editarComentario)
        val calificacion = findViewById<EditText>(R.id.inp_editarCalificacion)
        val recomendado: Boolean = radioTrue?.isChecked == true

        val reseniaEditada = Resenia(
            id.text.toString().toInt(),
            comentario.text.toString(),
            calificacion.text.toString().toInt(),
            recomendado,
            Date()
        )

        BaseDatosMemoria.productoElegido.resenia.forEachIndexed { index, resenia ->
            if (resenia.id == BaseDatosMemoria.reseniaElegida.id){
                BaseDatosMemoria.productoElegido.resenia[index] = reseniaEditada
            }
        }
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
