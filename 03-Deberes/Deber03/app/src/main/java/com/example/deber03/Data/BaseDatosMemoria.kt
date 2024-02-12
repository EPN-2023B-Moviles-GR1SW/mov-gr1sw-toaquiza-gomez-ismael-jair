package com.example.deber03.Data

import com.example.deber03.model.Correo
import com.example.deber03.R

class BaseDatosMemoria {
    companion object{
        val arregloCorreos = arrayListOf<Correo>()
        init {
            arregloCorreos.add(Correo("CLAVEMAT", "Convocatoria para tutores", "¡Oportunidad para los estudiantes de...", R.drawable.comp1))
            arregloCorreos.add(Correo("AQUIAMBI", "Invitación a conferencia", "Te invitamos a participar en la conferencia sobre...", R.drawable.comp2))
            arregloCorreos.add(Correo("BIRPAM", "Taller de investigación", "Regístrate en nuestro taller de investigación para...", R.drawable.comp3))
            arregloCorreos.add(Correo("NROGCOM", "Oferta de empleo", "¡Gran oportunidad de trabajo como programador en...", R.drawable.comp4))
            arregloCorreos.add(Correo("VATINTEG", "Actualización de datos", "Por favor, actualiza tus datos en nuestro sistema antes...", R.drawable.comp5))
            arregloCorreos.add(Correo("RONCITFRA", "Recordatorio de evento", "Recuerda que el evento sobre la transformación digital...", R.drawable.comp6))
        }
    }
}
