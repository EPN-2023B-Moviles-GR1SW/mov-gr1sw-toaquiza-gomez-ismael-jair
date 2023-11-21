import java.util.*
import kotlin.collections.ArrayList

fun main() {
    //Tipos de variables
    //INMUTABLES (NO se reasignan "=")
    val inmutable: String = "Adrian";

    //Mutables
    var mutable: String ="Vicente";
    mutable = "Adrian";

    //val > var

    var ejemploVariable = "Adrian";
    val edadEjemplo: Int = 12;
    ejemploVariable.trim();

    //Variable Primitiva
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'c'
    val mayorEdad: Boolean = true

    //Clases Javaa
    val  fechaNacimiento: Date = Date()

    //Switch
    val estadoCivilWhen = "c"
    when(estadoCivilWhen){
        ("c")->{
            print("casado")
        }
        "s" ->{
            print("soltero")
        }
        else-> {
            println("No sabemos")
        }
    }
    val coqueteo = if (estadoCivilWhen == "s") "Si" else "No"

    calcularSueldo(10.00)
    calcularSueldo(10.00,15.00)
    calcularSueldo(10.00, 12.00, 20.00)

    calcularSueldo(sueldo = 10.00,tasa = 12.00  , bonoEspecial = 20.00) //Parametros nombrados
    calcularSueldo(10.00, bonoEspecial = 20.00) //Named Parameters
    calcularSueldo( bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00) //Parametros nombrados

    val sumaUno = Suma(1,1)
    val sumaDos = Suma(null , 1)
    val sumaTres = Suma(1,null)
    val sumaCuatro = Suma(null,null)

    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()

    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    //Tipos de arreglos

    //Arreglos estaticos

    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)

    //Arreglos Dinamicos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)
    print(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    print(arregloDinamico)

    //FOREACH - UNIT
    // Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach{
            valorActual: Int ->
            println("Valor Actual: ${valorActual}")
        }
    arregloDinamico.forEach{ println(it) }

    arregloEstatico.forEachIndexed{
        index: Int, valorActual: Int ->
        println("valor ${valorActual} Indice: ${index}")
    }









    //MAP -> Muta el arreglo(Cambia el arreglo)
    //1) Enviemos el nuevo valo de la iteracion
    //2) Nos devuelve es un NUEVO ARREGLO con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico.map {
        valorActual: Int ->
        return@map valorActual.toDouble() + 100.00
    }

    println(respuestaMap)

    //Filter filtra el arreglo
    //1)Devuelve true o false
    //2)Nuevo arreglo filtrado

    val respuestaFilter: List<Int> = arregloDinamico.filter {
        valorActual: Int ->
        //Expresion condicion
        val mayoresACinco: Boolean = valorActual > 5
        return@filter mayoresACinco
    }
    val respuestFilterDos = arregloDinamico.filter{ it <= 5}
    println(respuestaFilter)
    println(respuestFilterDos)


    //OR AND
    //OR -> ANY (Alguno cumple?)
    //AND -> ALL (Todos cumplen?)
    val respuestaAny: Boolean = arregloDinamico.any {
        valorActual: Int ->
        return@any (valorActual > 5)
    }

    println(respuestaAny)

    val respuestasAll: Boolean = arregloDinamico.all {
            valorActual: Int ->
        return@all (valorActual > 5)
    }

    println(respuestasAll)

    // REDUCE -> Valor acumulado
    // Valor acumulado = 0 (Siempre 0 en lenguaje Kotlin)
    // [1, 2, 3, 4, 5] -> Sumeme todos los valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion 1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion 2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion 4
    // valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion 5
    val respuestaReduce: Int = arregloDinamico
        .reduce { // acumulado = 0 -> SIEMPRE EMPIEZA EN 0
                acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual) // -> Logica negocio
        }
    println(respuestaReduce) // 78
    // valorCarritoActual.cantidad *  valorCarritoActual.valor
    // 2 x 195
    // 1 x 10
    // 1 x 10



}


//void -> Unit

fun imprimirNombre(nombre: String): Unit{

    println("Nombre: ${nombre}")
}

fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double = 12.00, //Opcional
    bonoEspecial: Double? = null, // Opcion null -> nulleable
):Double{
    if (bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        bonoEspecial.dec()
        return sueldo * (100/tasa) + bonoEspecial
    }
}

abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(
        uno: Int,
        dos: Int
    ){
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }





}
abstract class Numeros(
    //Ejemplo:
    // uno: Int, // Paramentro sin modificador de acceso
    // private var uno: Int, // Propiedad Publica Clase numeros uno
    //var uno: Int, //Propiedad de la clase por defecto es Public
    // public var uno: Int,
    // Propiedad de la clase protected numeros.numeroUno
    protected  val numeroUno: Int,
    // Propiedad de la clase protected numeros.numeroDos
    protected val numeroDos: Int
){
    //var cedula : string = ""
    //private valor Calculado: Int = 0 private
    init {
        this.numeroUno; this.numeroDos; //this es opcional
        numeroUno; numeroDos; // sin el this, es lo mismo
        println("Inicializando")
    }
}



class Suma(// Constructor Primario SUma
    uno: Int,
    dos: Int
): Numeros(uno,dos){// Constructor del padre
    init {// Bloque constructor primario
        this.numeroUno; numeroUno;
        this.numeroDos; numeroDos;
    }
    constructor(
        uno: Int?,
        dos: Int
    ):this(
            if(uno == null) 0 else uno,
            dos
            ){
        numeroUno
    }
    constructor(
        uno: Int,
        dos: Int?
    ): this(
        uno,
        if(dos == null) 0 else uno
    )// Si no necesitamos al bloque de codigo "{}" lo omitimos

    constructor(
        uno: Int?,
        dos: Int?
    ): this(
        if(uno == null) 0 else uno,
        if(dos == null) 0 else uno
    )

    public fun sumar(): Int{
        val total = numeroUno+ numeroDos
        //Suma.agregarHistorial(total)
        agregarHistorial(total)
        return total
    }

    companion object{
        val pi =3.14
        fun elevarAlCuadrado(num: Int): Int{
            return num*num
        }

        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }
    }

}




