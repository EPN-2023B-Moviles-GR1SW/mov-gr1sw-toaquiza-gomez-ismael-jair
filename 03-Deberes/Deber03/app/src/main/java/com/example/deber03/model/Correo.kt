package com.example.deber03.model

class Correo (
    val titulo:String,
    var asunto: String,
    var cuerpo: String,
    var imagen: Int){
    override fun toString(): String {
        return "$titulo,$asunto,$cuerpo, $imagen"
    }
}