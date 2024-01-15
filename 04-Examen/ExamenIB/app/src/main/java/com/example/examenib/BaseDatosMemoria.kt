package com.example.examenib

import java.util.Date

class BaseDatosMemoria {
    companion object {
        val arregloProducto = arrayListOf<Producto>()
        val arregloResenia = arrayListOf<Resenia>()
        var productoElegido= Producto(0,"default","defaul",0.00,Date(), arregloResenia)
        var reseniaElegida = Resenia(1,"", 0, true, Date())

        init {
            val arregloReseniaP1 = arrayListOf<Resenia>()
            val arregloReseniaP2 = arrayListOf<Resenia>()
            val arregloReseniaP3 = arrayListOf<Resenia>()

            arregloReseniaP1.add(
                Resenia(
                    1, "Excelente Producto", 5, true, Date(2023 - 1900, 9, 13)
                )
            )

            arregloReseniaP2.add(
                Resenia(
                    1, "No me gustó mucho", 2, false, Date(2023 - 1900, 9, 15)
                )
            )

            arregloReseniaP3.add(
                Resenia(
                    1,"Buen monitor a un buen precio",4,true,Date(2023 - 1900, 9, 18)
                )
            )
            arregloProducto.add(
                Producto(
                    1, "Mouse", "Mouse entrada USB", 4.99, Date(2023 - 1900,1,22), arregloReseniaP1
                )
            )
            arregloProducto.add(
                Producto(
                    2, "Teclado", "Teclado de membranoa", 14.99, Date(2023 - 1900,2,20), arregloReseniaP2
                )
            )
            arregloProducto.add(
                Producto(
                    3, "Monitor", "Monitor 19 pulgadas", 139.99, Date(2023 - 1900, 10, 3),arregloReseniaP3
                )
            )
        }

    }
}