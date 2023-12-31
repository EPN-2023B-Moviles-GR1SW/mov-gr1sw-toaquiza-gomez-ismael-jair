import entities.Producto
import entities.Resenia
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat

class ReseniaOperations(private val archivo: String, private val archivoProductos: String) {

    fun crearResenia(resenia: Resenia) {
        val formatoFecha = SimpleDateFormat("yyyy/MM/dd")
        val fechaFormateada = formatoFecha.format(resenia.fechaPublicacion)

        val crear = "${resenia.id},${resenia.producto.id},${resenia.comentario}," +
                "${resenia.calificacion},${resenia.recomendado},$fechaFormateada\n"
        File(archivo).appendText(crear)
    }

    fun obtenerTodasLasResenias(): List<Resenia> {
        val listaResenias = mutableListOf<Resenia>()
        try {
            val lineas = File(archivo).readLines()
            for (linea in lineas) {
                val datos = linea.split(",")
                if (datos.size == 6) {
                    val idResenia = datos[0].toInt()
                    val idProducto = datos[1].trim().toInt()
                    val comentario = datos[2]
                    val calificación = datos[3].trim().toInt()
                    val recomendado = datos[4].toBoolean()
                    val fechaPublicación = SimpleDateFormat("yyyy/MM/dd").parse(datos[5])

                    val producto = obtenerProductoPorId(idProducto)
                    if (producto != null) {
                        val resenia = Resenia(idResenia, producto, comentario, calificación, recomendado, fechaPublicación)
                        listaResenias.add(resenia)
                    }
                }
            }
        } catch (ex: IOException) {
            println("Error al leer el archivo: ${ex.message}")
        }
        return listaResenias
    }


    fun obtenerTodasLasReseniasPorProducto(idProducto: Int): List<Resenia> {
        val listaReseniasPorProducto = mutableListOf<Resenia>()
        try {
            val lineas = File(archivo).readLines()
            for (linea in lineas) {
                val datos = linea.split(",")
                if (datos.size == 6) {
                    val idResenia = datos[0].toInt()
                    val idProductoResenia = datos[1].trim().toInt()
                    val comentario = datos[2]
                    val calificación = datos[3].trim().toInt()
                    val recomendado = datos[4].toBoolean()
                    val fechaPublicación = SimpleDateFormat("yyyy/MM/dd").parse(datos[5])

                    if (idProductoResenia == idProducto) {
                        val producto = obtenerProductoPorId(idProducto)
                        if (producto != null) {
                            val resenia = Resenia(idResenia, producto, comentario, calificación, recomendado, fechaPublicación)
                            listaReseniasPorProducto.add(resenia)
                        }
                    }
                }
            }
        } catch (ex: IOException) {
            println("Error al leer el archivo: ${ex.message}")
        }
        return listaReseniasPorProducto
    }

    fun actualizarResenia(idResenia: Int, nuevoComentario: String, nuevaCalificacion: Int, esRecomendado: Boolean) {
        val listaResenias = obtenerTodasLasResenias().toMutableList()
        val reseniaExistente = listaResenias.find { it.id == idResenia }

        if (reseniaExistente != null) {
            val productoAsociado = obtenerProductoPorId(reseniaExistente.producto.id)
            if (productoAsociado != null) {
                try {
                    reseniaExistente.comentario = nuevoComentario
                    reseniaExistente.calificacion = nuevaCalificacion
                    reseniaExistente.recomendado = esRecomendado


                    File(archivo).writeText("") //
                    listaResenias.forEach { crearResenia(it) }

                    println("Reseña con ID $idResenia actualizada exitosamente.")
                } catch (ex: IOException) {
                    println("Error al actualizar la reseña: ${ex.message}")
                }
            } else {
                println("El producto asociado a la reseña no fue encontrado.")
            }
        } else {
            println("La reseña con ID $idResenia no existe.")
        }
    }


    fun eliminarResenia(idResenia: Int) {
        val listaResenias = obtenerTodasLasResenias().toMutableList()
        val reseniaAEliminar = listaResenias.find { it.id == idResenia }
        if (reseniaAEliminar != null) {
            listaResenias.remove(reseniaAEliminar)
            try {
                File(archivo).writeText("")
                listaResenias.forEach { crearResenia(it) }
                println("Reseña con ID $idResenia eliminada correctamente.")
            } catch (ex: IOException) {
                println("Error al eliminar la reseña: ${ex.message}")
            }
        } else {
            println("La reseña con ID $idResenia no existe.")
        }
    }


    private fun obtenerProductoPorId(idProducto: Int): Producto? {
        try {
            val lineas = File(archivoProductos).readLines()
            for (linea in lineas) {
                val datos = linea.split(",")
                if (datos.size == 5 && datos[0].toInt() == idProducto) {
                    val id = datos[0].toInt()
                    val nombre = datos[1]
                    val descripcion = datos[2]
                    val precio = datos[3].toDouble()
                    val fechaCreacion = SimpleDateFormat("yyyy/mm/dd").parse(datos[4])
                    return Producto(id, nombre, descripcion, precio, fechaCreacion)
                }
            }
        } catch (ex: IOException) {
            println("Error al leer el archivo de productos: ${ex.message}")
        }
        return null
    }
}
