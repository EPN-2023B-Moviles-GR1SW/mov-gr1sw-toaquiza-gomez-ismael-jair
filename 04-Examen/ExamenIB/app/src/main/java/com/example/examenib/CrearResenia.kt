package com.example.examenib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import java.util.Date

class CrearResenia : AppCompatActivity() {

    var radioT:RadioButton?=null
    var radioF:RadioButton?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_resenia)

        radioT = findViewById(R.id.rdb_recom_true_editar)
        radioF = findViewById(R.id.rdb_recom_false_editar)
        val btnCrearNuevaResenia = findViewById<Button>(R.id.btn_crear_nuevares)
        btnCrearNuevaResenia.setOnClickListener{
            crearNuevaResenia()
            irActividad(ListViewResenias::class.java)
        }

        val btnCancelar = findViewById<Button>(R.id.btn_cancelar_crearres)
        btnCancelar.setOnClickListener{irActividad(ListViewProducto:: class.java)}
    }

    fun crearNuevaResenia(){
        val id = findViewById<EditText>(R.id.inp_idResenia)
        val comentario = findViewById<EditText>(R.id.inp_comentario)
        val calificacion = findViewById<EditText>(R.id.inp_calificacion)
        val recomendado: Boolean = radioT?.isChecked == true

        val nuevaResenia = Resenia(id.text.toString().toInt(),comentario.text.toString(),calificacion.text.toString().toInt(), recomendado, Date())
        BaseDatosMemoria.productoElegido.resenia.add(nuevaResenia)
    }


    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

}