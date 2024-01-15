package com.example.examenib

import java.text.SimpleDateFormat
import java.util.Date

class Producto(
    var id: Int,
    var nombre: String,
    var descripcion: String,
    var precio: Double,
    var fechaCreacion: Date,
    var resenia: MutableList<Resenia> = arrayListOf()
    ) {
    override fun toString(): String {
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy")

        // Formatea la fecha a una cadena en el formato especificado
        val fechaFormateada = formatoFecha.format(fechaCreacion)

        return "Producto:\nID:$id\nNombre:$nombre\nDescripcion:$descripcion\nPrecio:$precio\nFechaCreacion:$fechaFormateada"
    }
}