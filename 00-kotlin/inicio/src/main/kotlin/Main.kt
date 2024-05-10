import java.util.Date

fun main(){
    println("Hola mundo!")

    //Inmutables - no podemos re asignar valores
    //siempre preferir variables inmutables
    val inmutable: String = "Alejandra"

    //Mutables
    var mutable: String = "Daniela"
    mutable = "JUEVES"

    //Duck typing - no necesitamos especificar el tipo de dato si ya asignamos uno
    var ejemploVariable = " Alejandra Colcha "
    val edadEjemplo: Int = 21

    // ejemploVariable = edadEjemplo - ERROR
    val double: Double = 95.5
    val character: Char = 'A'
    val booleano : Boolean = true
    
    //Clases de Java
    val fechaNacimiento : Date = Date()


    //when (Switch)
    val estadoCivilWhen = "C"
    when (estadoCivilWhen) {
        ("C") ->{
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        }else->{
            println("No sabemos")
        }
    }
    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No"

    calcularSueldo(10.00 )
    calcularSueldo(10.00, 15.00, 12.00)
    //named parameters
    calcularSueldo(10.00, bonoEspecial = 20.00)
    calcularSueldo(tasa = 13.00, bonoEspecial = 20.00, sueldo = 10.00)
}

fun imprimirNombre(nombre:String): Unit{
    println("Nombre: $nombre") // Template String
}

fun calcularSueldo(
    sueldo:Double, // Requerido
    tasa: Double = 12.00, // opcional, valor por defecto
    bonoEspecial:Double? = null // Opcional nullable
) : Double{
    //Int -> Int? nullable

    if(bonoEspecial==null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) * bonoEspecial
    }
}

abstract class NumerosJava{
    protected val numeroUno:Int
    private val numeroDos:Int
    constructor(
        uno:Int,
        dos:Int
    ){
        this.numeroUno = uno
        this.numeroDos = dos
        print("Inicializando")
    }

}

abstract class Numeros(protected val numeroUno:Int, protected val numeroDos:Int){

    // Sin modificador de acceso se convierte en parámetro
    // Al usar private puede ser propiedad y parametro

    init {
            this.numeroUno
            this.numeroDos
            println("Inicializando")
    }
}

class Suma(parametroUno:Int, parametroDos: Int) :Numeros(parametroUno, parametroDos){

    public val SoyPublicoExplicito:String = "Explicito"
    val soyPublicoImplicito: String = "Implicito"

    init{
        //this.parametroDos - error no existe
        this.numeroUno
        this.numeroDos
        numeroUno // THIS opcional para propiedades y métodos
        numeroDos
        this.SoyPublicoExplicito
        soyPublicoImplicito
    }

    //public - es opcional
    fun sumar():Int{
        val total:Int = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }

    companion object{ // comparte entre todas las instancias
        val pi = 3.14

        fun elevarAlCuadrado(num:Int):Int{
            return num * num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorTotalSuma:Int){
            historialSumas.add(valorTotalSuma)
        }
    }
}

