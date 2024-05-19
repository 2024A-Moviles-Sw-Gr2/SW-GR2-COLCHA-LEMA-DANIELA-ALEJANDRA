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

    //Uso de clases
    val sumaUno = Suma(1,1)
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)
    val sumaCuatro = Suma(null, null)
    sumaUno.sumar();
    sumaDos.sumar();
    sumaTres.sumar();
    sumaCuatro.sumar();
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    // ARREGLOS ------------------------
    // Estáticos
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)
    //Dinamicos
    val arregloDinamico:ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9)
    println(arregloDinamico)

    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    //Operadores para iterar en arreglos
    // for each -> Unit
    println("Arreglo dinamico: ")
    val respuestaForEach: Unit = arregloDinamico.forEach{
        valorActual : Int -> print("${valorActual}, ")
    }
    println("\nArreglo estático:")
    // para funcines con un solo parámetro usamos IT
    arregloEstatico.forEach{ print(" ${it}, ") }

    //Operador MAP para modificar/cambiar el arreglo
    // map -> new array
    val respuestaMap : List<Double> = arregloDinamico.map{
        valorActual: Int ->
        return@map valorActual.toDouble() + 100.00
    }

    println("\n"+respuestaMap)
    val respuestaDos = arregloDinamico.map{it+15}
    println(respuestaDos)

    //FILTER -> filtrar arreglo
    // devuelve una expresión TRUE o FALSE
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayoresACinco: Boolean = valorActual >5
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    //OR AND
    // OR -> Any ¿Alguno cumple?
    // AND -> All ¿todos cumplen?

    val respuestaAny: Boolean = arregloDinamico.any{
        valorActual: Int ->
        return@any valorActual >5
    }
    println(respuestaAny)
    val respuestaAll: Boolean = arregloDinamico.all{
        it > 5
    }
    println(respuestaAll)


    //REDUCE -> valor acumulado
    //Siempre empieza en 0
    val respuestaReduce: Int = arregloDinamico
        .reduce{
            acumulado: Int, valorActual: Int ->
            return@reduce acumulado + valorActual
        }
    println("Valor acumulado: ${respuestaReduce}")
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


class Suma(parametroUno:Int,
           parametroDos: Int) :Numeros(parametroUno,
                                        parametroDos){

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

    // CONSTRUCTORES SECUNDARIOS ---------------------------------------------
    constructor(uno:Int?, dos:Int):this(
        if(uno== null) 0 else uno,
        dos
    )

    constructor(uno:Int, dos:Int?):this(
        uno,
        if(dos== null) 0 else dos
    )

    constructor(uno:Int?, dos:Int?):this(
        if(uno== null) 0 else uno,
        if(dos== null) 0 else dos
    )




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

