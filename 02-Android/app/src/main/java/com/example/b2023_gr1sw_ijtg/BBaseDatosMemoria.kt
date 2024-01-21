package com.example.b2023_gr1sw_ijtg

class BBaseDatosMemoria {

// EMPEZAR EL COMPANION OBJECT

    companion object {

        val arregloBEntrenador = arrayListOf<BEntrenador>()

        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1, "Ismael", "i@i.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2, "Toaquiza", "b@b.com")
                )

            arregloBEntrenador
                .add(
                    BEntrenador(3, "Carolina", "c@c.com")
                )
        }
    }

}