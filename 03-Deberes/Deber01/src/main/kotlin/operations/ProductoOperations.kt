package operations

import entities.Producto
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat

class ProductoOperations(private val archivo: String) {

    fun crearProducto(producto: Producto){
        val formatoFecha = SimpleDateFormat("yyyy/MM/dd")
        val fechaFormateada = formatoFecha.format(producto.fechaCreacion)

        val crear = "${producto.id}, ${producto.nombre}, ${producto.descripcion}," +
                "${producto.precio}, $fechaFormateada\n"

        File(archivo).appendText(crear)
    }
    fun obtenerTodosLosProductos(): List<Producto> {
        val listaProductos = mutableListOf<Producto>()
        try {
            val lineas = File(archivo).readLines()
            for (linea in lineas) {
                val datos = linea.split(",")
                if (datos.size == 5) {
                    val id = datos[0].toInt()
                    val nombre = datos[1]
                    val descripcion = datos[2]
                    val precio = datos[3].toDouble()
                    val fechaCreacion = SimpleDateFormat("yyyy/MM/dd").parse(datos[4])

                    val producto = Producto(id, nombre, descripcion, precio, fechaCreacion)
                    listaProductos.add(producto)
                }
            }
        } catch (ex: IOException) {
            println("Error al leer el archivo: ${ex.message}")
        }
        return listaProductos
    }

    fun actualizarProducto(producto: Producto) {
        val listaProductos = obtenerTodosLosProductos().toMutableList()
        val productoExistente = listaProductos.find { it.id == producto.id }
        if (productoExistente != null) {
            productoExistente.nombre = producto.nombre
            productoExistente.descripcion = producto.descripcion
            productoExistente.precio = producto.precio
            productoExistente.fechaCreacion = producto.fechaCreacion
            try {
                File(archivo).writeText("") // Limpiar el archivo
                listaProductos.forEach { crearProducto(it) } // Escribir los productos actualizados
            } catch (ex: IOException) {
                println("Error al actualizar el producto: ${ex.message}")
            }
        } else {
            println("El producto con ID ${producto.id} no existe.")
        }
    }

    fun eliminarProducto(idProducto: Int) {
        val listaProductos = obtenerTodosLosProductos().toMutableList()
        val productoAEliminar = listaProductos.find { it.id == idProducto }
        if (productoAEliminar != null) {
            listaProductos.remove(productoAEliminar)
            try {
                File(archivo).writeText("")
                listaProductos.forEach { crearProducto(it) }
            } catch (ex: IOException) {
                println("Error al eliminar el producto: ${ex.message}")
            }
        } else {
            println("El producto con ID $idProducto no existe.")
        }
    }



}