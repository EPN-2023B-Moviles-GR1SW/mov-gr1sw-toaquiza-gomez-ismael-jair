package entities

import operations.ProductoOperations
import java.util.Date

class Producto(
    var id: Int,
    var nombre: String,
    var descripcion: String,
    var precio: Double,
    var fechaCreacion: Date

) {


    private val productoOperations = ProductoOperations(System.getProperty("user.dir")+ "\\src\\main\\kotlin\\data\\Producto.txt")

    fun guardarProducto() {
        productoOperations.crearProducto(this)
        println("Guardando producto: $nombre")
    }

    fun obtenerProductos(): List<Producto> {
        return productoOperations.obtenerTodosLosProductos()
    }

    fun actualizarProducto() {
        productoOperations.actualizarProducto(this)
        println("Actualizando producto: $nombre")
    }

    fun eliminarProducto() {
        productoOperations.eliminarProducto(this.id)
        println("Eliminando producto: $nombre")
    }

    override fun toString(): String {
        return "Producto(id=$id, nombre='$nombre',descripcion='$descripcion', precio=$precio, fechaPublicacion='$fechaCreacion')"
    }

}