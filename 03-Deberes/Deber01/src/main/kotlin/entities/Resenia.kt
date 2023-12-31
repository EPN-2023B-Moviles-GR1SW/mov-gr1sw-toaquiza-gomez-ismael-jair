package entities

import ReseniaOperations
import java.util.Date

class Resenia(
    var id: Int,
    var producto: Producto,
    var comentario: String,
    var calificacion: Int,
    var recomendado: Boolean,
    var fechaPublicacion: Date
) {

    private val reseniaOperations = ReseniaOperations(System.getProperty("user.dir") + "\\src\\main\\kotlin\\data\\Resenia.txt",
        System.getProperty("user.dir") + "\\src\\main\\kotlin\\data\\Producto.txt")

    fun guardarResenia() {

        reseniaOperations.crearResenia(this)
        println("Guardando reseña: $id")
    }

    fun obtenerResenias(): List<Resenia> {
        return reseniaOperations.obtenerTodasLasResenias()
    }

    fun actualizarResenia() {
        reseniaOperations.actualizarResenia(this.id, this.comentario, this.calificacion, this.recomendado)
        println("Actualizando reseña: $id")
    }

    fun eliminarResenia() {
        reseniaOperations.eliminarResenia(this.id)
        println("Eliminando reseña: $id")
    }

    override fun toString(): String {
        return "Resenia(idReseña=$id, producto=${producto.nombre}, comentario='$comentario', " +
                "calificación=$calificacion, recomendado=$recomendado, fechaPublicación='$fechaPublicacion')"
    }


}