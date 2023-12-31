import entities.Producto
import entities.Resenia
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    var opcion: Int

    var archivoResenias = System.getProperty("user.dir") + "\\src\\main\\kotlin\\data\\Resenia.txt"
    var archivoProductos = System.getProperty("user.dir") + "\\src\\main\\kotlin\\data\\Producto.txt"

    do {
        println("Menú:")
        println("1. Menú Producto")
        println("2. Menú Reseña")
        println("3. Salir")
        print("Ingrese la opción deseada: ")

        opcion = scanner.nextInt()
        scanner.nextLine()

        when (opcion) {
            1 -> menuProducto(scanner, archivoResenias, archivoProductos)
            2 -> menuResenia(scanner, archivoResenias, archivoProductos)
            3 -> println("Saliendo del programa...")
            else -> println("Opción no válida. Inténtelo de nuevo.")
        }
    } while (opcion != 3)
}

fun menuProducto(scanner: Scanner, archivoResenias: String, archivoProductos: String) {
    var opcion: Int
    do {
        println("Menú Producto:")
        println("1. Crear Producto")
        println("2. Listar Productos")
        println("3. Actualizar Producto")
        println("4. Eliminar Producto")
        println("5. Volver al menú principal")
        print("Ingrese la opción deseada: ")

        opcion = scanner.nextInt()
        scanner.nextLine()

        when (opcion) {
            1 -> {
                println("Ingrese el ID del producto:")
                val idP = scanner.nextInt()
                scanner.nextLine()
                println("Ingrese el nombre del producto:")
                val nombre = scanner.nextLine()
                println("Ingrese la descripción del producto:")
                val descripcion = scanner.nextLine()
                println("Ingrese el precio del producto:")
                val precio = scanner.nextDouble()
                val fechaActual = Date()


                val producto = Producto(idP, nombre, descripcion, precio, fechaActual)
                producto.guardarProducto()
            }
            2 -> {
                println("Lista de Productos:")
                val productos = Producto(0, "", "", 0.0, Date()).obtenerProductos()
                productos.forEach { println(it) }
            }
            3 -> {
                println("Ingrese el ID del producto a actualizar:")
                val idActualizar = scanner.nextInt()
                scanner.nextLine()
                println("Ingrese el nuevo nombre del producto:")
                val nombreActualizar = scanner.nextLine()
                println("Ingrese la nueva descripción del producto:")
                val descripcionActualizar = scanner.nextLine()
                println("Ingrese el nuevo precio del producto:")
                val precioActualizar = scanner.nextDouble()
                val productoActualizar = Producto(idActualizar, nombreActualizar, descripcionActualizar, precioActualizar, Date())
                productoActualizar.actualizarProducto()
            }
            4 -> {
                println("Ingrese el ID del producto a eliminar:")
                val idEliminar = scanner.nextInt()
                val productoEliminar = Producto(idEliminar, "", "", 0.0, Date())
                productoEliminar.eliminarProducto()
            }
            5 -> println("Volviendo al menú principal...")
            else -> println("Opción no válida. Inténtelo de nuevo.")
        }
    } while (opcion != 5)
}

fun menuResenia(scanner: Scanner, archivoResenias: String, archivoProductos: String) {
    var opcion: Int
    do {
        println("Menú Reseña:")
        println("1. Crear Reseña")
        println("2. Listar Reseñas por Producto")
        println("3. Actualizar Reseña")
        println("4. Eliminar Reseña")
        println("5. Volver al menú principal")
        print("Ingrese la opción deseada: ")

        opcion = scanner.nextInt()
        scanner.nextLine()

        when (opcion) {
            1 -> {
                println("Ingrese el ID de la Reseña:")
                val idR = scanner.nextInt()
                scanner.nextLine()
                println("Ingrese el ID del producto de la reseña:")
                val idProductoResenia = scanner.nextInt()
                scanner.nextLine()
                println("Ingrese el comentario de la reseña:")
                val comentario = scanner.nextLine()
                println("Ingrese la calificación de la reseña:")
                val calificacion = scanner.nextInt()
                println("¿Es recomendada la reseña? (true/false):")
                val recomendado = scanner.nextBoolean()
                val resenia = Resenia(idR, Producto(idProductoResenia, "", "", 0.0, Date()), comentario, calificacion, recomendado, Date())
                resenia.guardarResenia()
            }
            2 -> {
                println("Ingrese el ID del producto para ver las reseñas:")
                val idProducto = scanner.nextInt()

                val reseniaOperations = ReseniaOperations(System.getProperty("user.dir") + "\\src\\main\\kotlin\\data\\Resenia.txt", System.getProperty("user.dir") + "\\src\\main\\kotlin\\data\\Producto.txt")

                val resenias = reseniaOperations.obtenerTodasLasReseniasPorProducto(idProducto)

                if (resenias.isNotEmpty()) {
                    println("Reseñas del producto $idProducto:")
                    resenias.forEach { println(it) }
                } else {
                    println("No se encontraron reseñas para el producto con ID: $idProducto")
                }
            }
            3 -> {
                println("Ingrese el ID de la reseña a actualizar:")
                val idReseniaActualizar = scanner.nextInt()
                scanner.nextLine()
                println("Ingrese el nuevo comentario de la reseña:")
                val comentarioActualizar = scanner.nextLine()
                println("Ingrese la nueva calificación de la reseña:")
                val calificacionActualizar = scanner.nextInt()
                println("¿Es recomendada la reseña? (true/false):")
                val recomendadoActualizar = scanner.nextBoolean()
                val reseniaOperations = ReseniaOperations(archivoResenias, archivoProductos)
                reseniaOperations.actualizarResenia(idReseniaActualizar, comentarioActualizar, calificacionActualizar, recomendadoActualizar)
            }
            4 -> {
                println("Ingrese el ID de la reseña a eliminar:")
                val idReseniaEliminar = scanner.nextInt()

                val reseniaOperations = ReseniaOperations(archivoResenias, archivoProductos)
                reseniaOperations.eliminarResenia(idReseniaEliminar)
            }
            5 -> println("Volviendo al menú principal...")
            else -> println("Opción no válida. Inténtelo de nuevo.")
        }
    } while (opcion != 5)
}
