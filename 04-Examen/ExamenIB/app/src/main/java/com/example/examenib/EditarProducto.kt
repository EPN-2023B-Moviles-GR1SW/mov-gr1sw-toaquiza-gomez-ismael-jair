package com.example.examenib

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.util.Date

class EditarProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_producto)

        val id = findViewById<EditText>(R.id.inp_editarIdProducto)
        val nombre = findViewById<EditText>(R.id.inp_editarNombre)
        val desc = findViewById<EditText>(R.id.inp_editarDesc)
        val precio = findViewById<EditText>(R.id.inp_editarPrecio)

        id.setText(BaseDatosMemoria.productoElegido.id.toString())
        nombre.setText(BaseDatosMemoria.productoElegido.nombre)
        desc.setText(BaseDatosMemoria.productoElegido.descripcion)
        precio.setText(BaseDatosMemoria.productoElegido.precio.toString())

        val btnEditarProducto = findViewById<Button>(R.id.btn_editar_prodexist)
        btnEditarProducto.setOnClickListener{
            editarProducto()
            irActividad(ListViewProducto::class.java)
        }

        val btnCancelar = findViewById<Button>(R.id.btn_cancelar_editarprod)
        btnCancelar.setOnClickListener {
            irActividad(ListViewProducto::class.java)
        }


    }

    fun editarProducto() {
        val id = findViewById<EditText>(R.id.inp_editarIdProducto)
        val nombre = findViewById<EditText>(R.id.inp_editarNombre)
        val desc = findViewById<EditText>(R.id.inp_editarDesc)
        val precio = findViewById<EditText>(R.id.inp_editarPrecio)

        val productoEditado = Producto(
            id.text.toString().toInt(),
            nombre.text.toString(),
            desc.text.toString(),
            precio.text.toString().toDouble(),
            Date(),
            BaseDatosMemoria.productoElegido.resenia
        )

        BaseDatosMemoria.arregloProducto.forEachIndexed { index, producto ->
            if (producto.id === BaseDatosMemoria.productoElegido.id){
                BaseDatosMemoria.arregloProducto[index] = productoEditado
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