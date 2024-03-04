package com.example.examenib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.examenib.databasefrs.FirestoreProducto
import com.example.examenib.model.Producto
import java.util.Date

class CrearProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_producto)

        val btnCrearNuevoProducto = findViewById<Button>(R.id.btn_crear_nuevoprod)
        btnCrearNuevoProducto.setOnClickListener {
            crearNuevoProducto()
            irActividad(ListViewProducto::class.java)
        }

        val btnCancelar = findViewById<Button>(R.id.btn_cancelar_crearprod)
        btnCancelar.setOnClickListener { irActividad(ListViewProducto::class.java) }
    }

    fun crearNuevoProducto() {
        val id = findViewById<EditText>(R.id.inp_idProducto)
        val nombre = findViewById<EditText>(R.id.inp_nombre)
        val descripcion = findViewById<EditText>(R.id.inp_desc)
        val precio = findViewById<EditText>(R.id.inp_precio)

        val nuevoProducto = Producto(
            id.text.toString(),
            nombre.text.toString(),
            descripcion.text.toString(),
            precio.text.toString().toLong(),
            Date()
        )

        FirestoreProducto.crearProducto(nuevoProducto)
    }


    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}