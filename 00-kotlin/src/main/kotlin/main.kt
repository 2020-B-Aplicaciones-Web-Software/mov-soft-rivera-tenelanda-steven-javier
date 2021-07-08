import java.util.*
import kotlin.collections.ArrayList

fun main () {
    println("Hola mundo")
    //JAVA int edad = 12;

    //Duck Typing

    var edadProfesor = 31

    var sueldoProfesor = 12.1

    //Se tiene variables
    //MUTABLES (Re asignar) / INMUTABLES (No Re asignar)

    //MUTABLES
    var edadCachoro: Int = 0
    edadCachoro = 1
    edadCachoro = 2
    edadCachoro = 3

    //INMUTABLES
    val numeroCedula = 18181818181818
    // numeroCedual = 12

    //Tipos de Variables son los mismo que tenemos en (JAVA) -> Primitivos y los no Primitivos
    //Int Double Float Boolean Char
    val nombreProfesor: String = "Steven Rivera"
    val sueldo: Double = 12.2
    val estadoCivil: Char = 'S'
    val casado: Boolean = false
    val fechaNacimiento: Date = Date ()

    //Condicionales
    if (true) {

    } else {

    }
    //switch en kotlin mas amigable con los programadores

    when(estadoCivil){
        ('C') -> {
            println("Huir")
        }
        'S'-> {
            println("Conversar")
        }
        'N' -> {
            println("Nada")
        }
        'p' -> println("Profesor")
        else -> {
            println("No tiene estado civil")
        }
    }

    //if y else dentro de la misma linea
    val sueldoMayorAEstablecido = if (sueldo > 12.2) 500 else 0
    // condicion ? bloque-true : bloque-false

    //imprimirNombre("Steven")
    imprimirNombre("Steven")

    calcularSueldo(100.00)
    calcularSueldo(100.00,14.00)
    calcularSueldo(100.00,14.00, 25.00)


    // Named Parameters
    calcularSueldo(
        bono = 3.00,

        sueldo = 1000.00
    )

    calcularSueldo(
        tasa = 3.00,
        bono = 3.00,
        sueldo = 1000.00
    )

    //Arreglo Estaticos
    val arregloEstatico: Array<Int> = arrayOf(1, 2, 3)
    //arregloEstatico.add(12) -> NO TENEMOS AQUI, NO SE PUEDE MODIFICAR LOS ELEMENTOS DEL ARREGLO

    //Arreglo Dinamicos
    val arregloDinamico: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    //OPERADORES -> Sirven para los arreglos estaticos y dinamicos
//Son funciones que sirven para cualquier tipo de iterable
//Todos los operadores devuelven algo

//FOR EACH -> Unit
//Iterar un arreglo
//No es necesario lo siguiente:
    val respuestaForEach: Unit = arregloDinamico

        .forEach {valorActual: Int ->
            println("Valor actual: ${valorActual}")
        }
    println(respuestaForEach)
    arregloDinamico
        .forEach {  It -> Int
            //it -> Tipo de dato
            println()
        }

    arregloDinamico.forEach { println("Valor actual: ${it}")}

    // FOR EACH -> INDEXED

    arregloDinamico
        .forEachIndexed{indice:Int, valorActual:Int ->
            println("Valor actual: ${valorActual}, indice actual: ${indice}")
        }

    // MAP -> Muta el arreglo (Cambia el arreglo)
    //Ayuda a modificar o cambiar el arreglo
    //MAP nos devuelve el arreglo cambiado
    //MAP -> List<....>

    //1) Enviemos el nuevo valor de la iteracion
    //2)Nos devuelve un NUEVO ARREGLO con los valores modficados

    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble()
        }
    println(respuestaMap)

    val respuestaMapDos: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }
    println(respuestaMapDos)

    val respuestaMapTres: List<Date> = arregloDinamico
        .map { valorActual: Int ->
            return@map Date()
        }
    println(respuestaMapTres)

    //Filter -> FILTRAR EL ARREGLO
    //Filter -> Arreglo Filtrado
    //1) Devolve una expresion (TRUE O FALSE)
    //2) Nuevo arreglo filtrado

    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5 //Expresion Condicional
            return@filter mayoresACinco // Boolean
        }

    val respuestaFilterDos: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val menorIgualACinco: Boolean = valorActual <= 5 //Expresion Condicional
            return@filter menorIgualACinco // Boolean
        }

    println(respuestaFilter)
    println(respuestaFilterDos)

    // OR AND

    //OR -> ANY (Alguno cumple?)
    //AND -> ANY (Todos cumplen?)

    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) // True

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) // False

    //Operador Reduce

    //REDUCE -> Valor acumulado
    // Valor acumulado = 0 (Siempre 0 en lenguaje kotlin)
    //[1, 2, 3, 4, 5] -> Sumeme todos los valores del arreglo
    //valorIteracion1 = valorEmpieza + 1 = 0 +1 = 1 -> Iteracion 1
    //valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion 2
    //valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 3
    //valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion 4

    val respuestaReduce: Int = arregloDinamico
        .reduce {// acumulado = 0 -> SIEMPRE EMPIEZ EN 0
            acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual) // ->Logico negocio
        }
    println(respuestaReduce) // 78

    //Modificar el valor acumulado inicial
    //Para esto se necesita de otro arreglo

    //100
    //[12, 15, 8, 10]
    val arregloDanio = arrayListOf<Int>(12, 15, 8, 10)
    val respuestaReduceFold = arregloDanio
        .fold(
            100, //acumulado inicial
            {acumulado, valorActualIteracion ->
                return@fold acumulado -valorActualIteracion
            }
        )
    println(respuestaReduceFold)

    val vidaActual: Double = arregloDinamico
        .map { it * 2.3 } // arreglo
        .filter { it > 20 } // arreglo
        .fold(100.00, {acc, i -> acc - i}) //valor
        .also { println(it) } // ejecutar codigo extra
    println("Valor vida actual ${vidaActual}") // 3.4

    //CLASE MARTES 15-05-2021

    val ejemploUno = Suma (1,2)
    //val ejemploUno = Suma(1,2)
    val ejemploDos = Suma (null,2)
    //val ejemploDos = Suma(null,2)
    val ejemploTres = Suma (1,null)
    //val ejemploTres = Suma(1,null)
    val ejemploCuatro = Suma (null,null)
    //val ejemploCuatro = Suma(null,null)
    println(ejemploUno.sumar())
    println(ejemploDos.sumar())
    println(ejemploTres.sumar())
    println(ejemploCuatro.sumar())

    //Se puede acceder normalemente a los metodos del companion object
    //Suma.historialSumas
    //Suma.agregarHistorial(1)
    println(Suma.historialSumas)






}//Fin bloque MAIN

//FUNCIONES
//En kotlin no existe el void en funciones existe el Unit
//void imprimirNombre(Int nombre){}
fun imprimirNombre(nombre: String): Unit {
    println("Nombre ${nombre}") //Template String
}

//Parametros requeridos y opcionales
fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double = 12.00, //Opcionales - Defecto
    bono: Double? = null // Variables que PUEDEN ser null
): Double{
    // si el string puede ser nulo se pone ? al final
    //String -> String?
    //Int -> Int?
    //Date -> Date?
    if(bono != null){
        return sueldo * (100 / tasa) + bono
    }else{
        return sueldo * (100 / tasa)
    }

}

abstract class NumerosJava {
    protected val numeroUno: Int //Propiedad clase
    private val numeroDos: Int ////Propiedad clase
    constructor(
        uno: Int, //Parametros requeridos
        dos: Int, //Parametros requeridos
    ){
        //this.numeroUno = uno
        //this.numeroDos = dos
        numeroUno = uno
        numeroDos = dos
        println("Inicializar")
    }
}

//instancia.numeroUno
//instancia.numeroDos

abstract class Numeros ( //Constructor Primario
    //modificadores de acceso
    protected var numeroUno: Int, //Propiedad clase
    protected var numeroDos: Int, //Propiedad clase
){
    init { //Bloque inicio del constructor primario
        println("Inicializar")
    }
}
//instancia.numeroUno
//instancia.numeroDos

//Creacion de la clase SUMA
class Suma (// Constructor primario
    uno: Int, //Parametro requerido
    dos: Int, //Parametro requerido
):Numeros( //Constructor "papa" (super)
    uno,
    dos
){
    init {
        this.numeroUno
        this.numeroDos
        //x -> this.uno -> NO EXISTEN
        //x -> this.dos -> NO EXISTEN
    }

    constructor(//Segundo constructor
        uno: Int?, //parametros
        dos: Int // parametros
    ) : this( //llamada constructor primario
        if (uno == null) 0 else uno,
        dos
    )

    //ACEPTAMOS UN ENTERO Y UN NULO
    constructor(//Tercer constructor
        uno: Int, //parametros
        dos: Int? // parametros
    ) : this( //llamada constructor primario
        uno,
        if (dos == null) 0 else dos,
    )
    //ACEPTAMOS 2 NULOS
    constructor(//Cuarto constructor
        uno: Int?, //parametros
        dos: Int? // parametros
    ) : this( //llamada constructor primario
        if (uno == null) 0 else uno,
        if (dos == null) 0 else dos,
    )

    //Definir funciones o metodos dentro de la clase
    //public fun sumar(): Int {
    fun sumar(): Int {
        //Como no es necesario el this se comenta esta parte
        //val total: Int = this.numeroUno + this.numeroDos
        val total: Int = numeroUno + numeroDos
        return total
    }

    //SINGLETON
    companion object{
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma:Int){
            //this.historialSumas.add(valorNuevaSuma)
            historialSumas.add(valorNuevaSuma)
            println(historialSumas)
        }
    }


}







