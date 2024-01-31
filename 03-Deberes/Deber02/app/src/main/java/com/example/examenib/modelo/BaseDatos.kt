package com.example.examenib.modelo

import com.example.examenib.Producto
import com.example.examenib.Resenia
import java.util.Date

class BaseDatos {
    companion object {
        var tablaProducto: SqliteHelperProducto ?= null
        var tablaResenia: SqliteHelperResenia ? = null
        var productoElegido= Producto(0,"default","defaul",0.00,Date(), arrayListOf())
        var reseniaElegida = Resenia(1,"", 0, true, Date())
        var productoSeleccionadoId: Int? = null

        // Método para seleccionar un producto
        fun seleccionarProducto(id: Int) {
            productoSeleccionadoId = id
        }


    }
}