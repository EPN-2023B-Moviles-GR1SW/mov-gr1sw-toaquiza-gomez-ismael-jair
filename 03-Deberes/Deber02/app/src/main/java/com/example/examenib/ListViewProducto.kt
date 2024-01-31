package com.example.examenib

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.examenib.modelo.BaseDatos
import com.example.examenib.modelo.SqliteHelperProducto
import com.example.examenib.modelo.SqliteHelperResenia
import com.google.android.material.snackbar.Snackbar

class ListViewProducto : AppCompatActivity() {
    var arregloProducto: MutableList<Producto>? = null
    var posicionItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_producto)
        BaseDatos.tablaProducto = SqliteHelperProducto(this)
        BaseDatos.tablaResenia = SqliteHelperResenia(this)


        cargarProductos()
        val btnAnadirListView = findViewById<Button>( R.id.btn_crear_prod)
        btnAnadirListView.setOnClickListener{
            agregarProducto()
        }
        val listView = findViewById<ListView>(R.id.lv_producto)

        registerForContextMenu(listView)

    }

    private fun cargarProductos(){
        val listView = findViewById<ListView>(R.id.lv_producto)
        arregloProducto = BaseDatos.tablaProducto!!.getProductos()
        if (arregloProducto != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                arregloProducto!!
            )
            listView.adapter = adapter
            adapter.notifyDataSetChanged()
            registerForContextMenu(listView)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_producto, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItem = posicion
        mostrarSnackbar("${posicion}")
        BaseDatos.productoElegido = arregloProducto!![posicion]
        BaseDatos.productoElegido.resenia = BaseDatos.tablaResenia?.getReseniasDeProducto(BaseDatos.productoElegido.id)!!
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mn_editar_prod ->{
                mostrarSnackbar("${posicionItem}")
                irActividad(EditarProducto::class.java)
                return true
            }
            R.id.mn_eliminar_prod -> {
                mostrarSnackbar("${posicionItem}")
                abrirDialogo()
                return true
            }
            R.id.mn_ver_resenias -> {
                mostrarSnackbar("${posicionItem}")
                irActividad(ListViewResenias::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)

        }
    }

    fun agregarProducto() {
        irActividad(CrearProducto:: class.java)
        cargarProductos()
    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{ dialog, which ->
                mostrarSnackbar("Acepto ${which}")
                eliminarProducto()

            }
        )

        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val dialogo = builder.create()
        dialogo.show()
    }

    fun eliminarProducto (){
        BaseDatos.tablaProducto!!.eliminarProducto(BaseDatos.productoElegido.id)
        cargarProductos()
    }
    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(findViewById(R.id.lv_producto),
            texto, Snackbar.LENGTH_LONG)
        snack.show()
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
