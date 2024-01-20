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
import com.google.android.material.snackbar.Snackbar

class ListViewProducto : AppCompatActivity() {
    val arregloProducto = BaseDatosMemoria.arregloProducto
    var posicionItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_producto)

        val listView = findViewById<ListView>(R.id.lv_producto)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloProducto
        )

        listView.adapter =adaptador
        adaptador.notifyDataSetChanged()

        val btnAnadirListView = findViewById<Button>( R.id.btn_crear_prod)
        btnAnadirListView.setOnClickListener{
            agregarProducto(adaptador)
        }
        registerForContextMenu(listView)

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
        BaseDatosMemoria.productoElegido = arregloProducto[posicion]
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

    fun agregarProducto( adaptador: ArrayAdapter<Producto>) {
        irActividad(CrearProducto:: class.java)
        adaptador.notifyDataSetChanged()
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
        val listView = findViewById<ListView>(R.id.lv_producto)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloProducto
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        arregloProducto.remove(BaseDatosMemoria.productoElegido)
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
