import java.util.*

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
}



