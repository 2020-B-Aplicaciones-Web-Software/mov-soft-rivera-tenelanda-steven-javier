import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

fun main(){
    val receta_medica = "C:/Users/steve/OneDrive/Desktop/Escuela Politecnica Nacional/2021-A/APLICACIONES MOVILES/2021-A/mov-soft-rivera-tenelanda-steven-javier/Deber_01/src/main/kotlin/receta_medica.txt"
    val medicamento = "C:/Users/steve/OneDrive/Desktop/Escuela Politecnica Nacional/2021-A/APLICACIONES MOVILES/2021-A/mov-soft-rivera-tenelanda-steven-javier/Deber_01/src/main/kotlin//medicamento.txt"

    val scanner = Scanner(System.`in`)

    do{ print(
            "\n====== MENU PRINCIPAL =====\n"+
            "Seleccionar un archivo:\n" +
            "1. Receta Medica\n" +
            "2. Medicamento\n" +
            "3. Salir\n" +
            "Opción: ")
        val opcion = scanner.nextLine()

    when (opcion) {
        ("1") -> {
            do{
            print(
                "\n================= MENU RECETA MEDICA =================\n" +
                "Que desea realizar sobre el archivo Receta Medica:\n" +
                        "1. Leer las recetas medicas\n" +
                        "2. Buscar una receta medica \n" +
                        "3. Crear una receta medica\n" +
                        "4. Eliminar una receta medica\n" +
                        "5. Actualizar una receta medica\n" +
                        "6. Salir\n" +
                        "Opción:  "
            )

            val opcion2 = scanner.nextLine()

            when (opcion2) {

                ("1") -> {
                    println("======================================================")
                    imprimir_receta_medica(leer_receta_medica(receta_medica))

                }

                ("2") -> {
                    println("======================================================")
                    print(
                        "Seleccione el campo por el cual desea realizar la busqueda: \n" +
                                "1. Identificador de la receta medica\n" +
                                "2. Fecha de emision de la receta medica\n" +
                                "3. Nombre del paciente\n" +
                                "4. Edad del paciente\n" +
                                "5. Diagnostico del paciente\n" +
                                "Opcion: "
                    )
                    val opcion_busqueda = scanner.nextLine()
                    println("Ingrese el parametro de búsqueda: \n")
                    val parametro_busqueda = scanner.nextLine()
                    imprimir_receta_medica(
                        encontrar_receta_medica(
                            opcion_busqueda.toInt(),
                            parametro_busqueda,
                            leer_receta_medica(receta_medica)
                        )
                    )
                }

                ("3") -> {
                    println("======================================================")
                    print("Las siguientes recetas medicas se encuentran en el archivo: \n")
                    imprimir_receta_medica(leer_receta_medica(receta_medica))
                    crear_receta_medica(receta_medica)
                }

                ("4") -> {
                    println("======================================================")
                    print(
                                "1. Busqueda de recetas medicas para eliminar \n" +
                                "2. Eliminar una receta medica mediante su identificador \n" +
                                "Opción: "
                    )
                    val opcion_eliminar_receta_medica = scanner.nextLine()
                    when (opcion_eliminar_receta_medica) {
                        ("1") -> {

                            print(
                                "Las recetas medicas en el archivo son las siguientes: \n"
                            )
                            imprimir_receta_medica(leer_receta_medica(receta_medica))
                            borrar_receta_medica(receta_medica, leer_receta_medica(receta_medica))
                        }
                        ("2") -> {
                            borrar_receta_medica(receta_medica, leer_receta_medica(receta_medica))
                        }
                    }
                }
                ("5") -> {
                    println("======================================================")
                    actualizar_receta_medica(leer_receta_medica(receta_medica), receta_medica)
                    print("La receta medica se actualizo con exito.")
                }
            }
            }while (opcion2 != "6")
        }

        ("2") -> {
            do{
            print(
                "\n================== MENU MEDICAMENTO ==============\n" +
                "Que desea realizar sobre el archivo Medicamento:\n" +
                    "1. Leer\n" +
                    "2. Buscar \n" +
                    "3. Crear un nuevo registro de medicamento\n" +
                    "4. Eliminar un medicamento\n" +
                    "5. Actualizar un medicamento\n" +
                    "6. Salir\n" +
                    "Opción:  ")

            val opcion2 = scanner.nextLine()

            when (opcion2) {

                ("1") -> {
                    println("==================================================")
                    imprimir_medicamento(leer_medicamento(medicamento))
                }

                ("2") -> {
                    println("==================================================")
                    print(
                        "Seleccione el campo por el cual desea realizar la busqueda: \n" +
                                "0. Identificador del medicamento\n" +
                                "1. Nombre del medicamento\n" +
                                "2. Concentracion del medicamento\n" +
                                "3. Via de administración del medicamento\n" +
                                "4. Forma farmaceutica del medicamento\n" +
                                "5. Venta libre del medicamento\n" +
                                "Si el medicamento es de venta libre escriba 'true' caso contrario escriba 'false'\n" +
                                "Opcion: "
                    )
                    val campo_busqueda_medicamento = scanner.nextLine()
                    println("Ingrese el parametro de búsqueda: \n")
                    val nuevo_campo_medicamento = scanner.nextLine()
                    imprimir_medicamento(
                        encontrar_medicamento(
                            campo_busqueda_medicamento.toInt(),
                            nuevo_campo_medicamento,
                            leer_medicamento(medicamento)
                        )
                    )
                }

                ("3") -> {
                    println("==================================================")
                    print("Las siguientes medicamentos se encuentran en el archivo: \n")
                    imprimir_medicamento(leer_medicamento(medicamento))
                    crear_medicamento(medicamento)
                }

                ("4") -> {
                    println("==================================================")
                    print(
                        "1. Busqueda de medicamentos para eliminar \n" +
                                "2. Eliminar el medicamento mediante su identificador \n" +
                                "Opción: "
                    )
                    val opcion_eliminar_medicamento = scanner.nextLine()
                    when (opcion_eliminar_medicamento) {
                        ("1") -> {

                            print(
                                "Los medicamento en el archivo son los siguientes: \n"
                            )
                            imprimir_medicamento(leer_medicamento(medicamento))
                            borrar_medicamento(medicamento, leer_medicamento(medicamento))
                        }

                        ("2") -> {
                            borrar_medicamento(medicamento, leer_medicamento(medicamento))
                        }
                    }
                }
                ("5") -> {
                    println("==================================================")
                    actualizar_medicamento(leer_medicamento(medicamento), medicamento)
                    print("Medicamento actualizado con exito.")
                }
            }
            }while(opcion2 != "6")
        }
    }
    }while (opcion != "3")
}


//FUNCIONES PARA BORRAR
fun borrar_receta_medica(archivo:String , archivo1: ArrayList<Receta_Medica>){
    val scanner = Scanner(System.`in`)
    var identificador: String?
    var nombre:String?
    var edad:String?
    var diagnostico:String?
    var frecuencia_duracion_tratamiento:String?
    println("Ingrese el identificador de la receta medica a eliminar")
    val opcion =   scanner.nextLine()
    val borrar = encontrar_receta_medica(1, opcion, archivo1)
    borrar.forEach {
        identificador = it.getIdentificador().toString()
        val formato = SimpleDateFormat("dd/mm/yyyy")
        val fecha : String? = formato.format(it.getFecha())
        nombre = it.getNombre()
        edad = it.getEdad().toString()
        diagnostico = it.getDiagnostico()
        frecuencia_duracion_tratamiento = it.getFrecuencia_duracion_tratamiento()
        borrar_fila(archivo,"${identificador},${fecha},${nombre},${edad},${diagnostico},${frecuencia_duracion_tratamiento},")
        println("Se ha eliminado la receta medica ingresada")

    }
}
fun borrar_medicamento(archivo:String , archivo1: ArrayList<Medicamento>){
    val scanner = Scanner(System.`in`)
    var identificador: String?
    var nombre:String?
    var concentracion:String?
    var via_administracion:String?
    var forma_farmaceutica:String?
    var venta_libre:String?

    println("Ingrese el identificador del medicamento a eliminar")
    val opcion =   scanner.nextLine()
    val borrar = encontrar_medicamento(0, opcion, archivo1)
    borrar.forEach {
        identificador = it.getIdentificador().toString()
        nombre = it.getNombre()
        concentracion = it.getConcentracion().toString()
        via_administracion = it.getVia_administracion()
        forma_farmaceutica = it.getForma_farmaceutica()
        venta_libre = it.getVenta_libre().toString()
        borrar_fila(archivo,"${identificador},${nombre},${concentracion},${via_administracion},${forma_farmaceutica},${venta_libre},")
        println("Se ha eliminado el medicamento ingresado")

    }
}

//FUNCIONES PARA CREAR
fun crear_receta_medica( ruta: String ){
    val scanner = Scanner(System.`in`)

    println("Ingrese el identificador de la receta medica:")
    val identificador = scanner.nextLine()
    println("Ingrese la fecha de la receta medica:")
    val fecha = scanner.nextLine()
    println("Ingrese el nombre del paciente:")
    val nombre = scanner.nextLine()
    println("Ingrese la edad del paciente:")
    val edad = scanner.nextLine()
    println("Ingrese el diagnostico del paciente:")
    val diagnostico = scanner.nextLine()
    println("Ingrese la frecuencia y duracion del tratamiento:")
    val frecuencia_duracion_tratamiento = scanner.nextLine()

    try {
        FileWriter(ruta, true).use { fw ->
            BufferedWriter(fw).use { bw ->
                PrintWriter(bw).use { out ->
                    out.print("\n"+"${identificador},${fecha},${nombre},${edad},${diagnostico},${frecuencia_duracion_tratamiento},")
                }
            }
        }
    } catch (e: IOException) {
        //exception handling left as an exercise for the reader
    }
    borrar_fila(ruta,"")

    println("Se ha creado la receta medica")


}

fun crear_medicamento( ruta: String ){
    val scanner = Scanner(System.`in`)

    println("Ingrese el identificador del medicamento:")
    val identificador = scanner.nextLine()
    println("Ingrese el nombre del medicamento:")
    val nombre = scanner.nextLine()
    println("Ingrese la concentración del medicamento:")
    val concentracion = scanner.nextLine()
    println("Ingrese la via de administración del medicamento:")
    val via_administracion = scanner.nextLine()
    println("Ingrese la forma farmaceutica del medicamento:")
    val forma_farmaceutica = scanner.nextLine()
    println("Ingrese un valor de 'True' si el medicamento es de venta libre, caso contrario ingrese 'False':")
    val venta_libre = scanner.nextLine()

    try {
        FileWriter(ruta, true).use { fw ->
            BufferedWriter(fw).use { bw ->
                PrintWriter(bw).use { out ->
                    out.print("\n"+"${identificador},${nombre},${concentracion},${via_administracion},${forma_farmaceutica},${venta_libre},")
                }
            }
        }
    } catch (e: IOException) {
        //exception handling left as an exercise for the reader
    }
    borrar_fila(ruta,"")

    println("Se ha creado un nuevo medicamento")

}

//FUNCIONES PARA LEER
fun leer_archivo(nombreArchivo: String):ArrayList<ArrayList<String>> {
    val uno = ArrayList<String>()
    val varios = arrayListOf(ArrayList<String>())

    try {
        val myObj = File(nombreArchivo)
        val myReader = Scanner(myObj)
        //myReader.nextLine()
        while (myReader.hasNextLine()) {
            val data: String = myReader.nextLine()
            val st = StringTokenizer(data, ",")
            while (st.hasMoreTokens()) {
                uno.add(st.nextToken())
            }

            varios.add(uno.clone() as ArrayList<String>)
            uno.clear()
        }
        myReader.close()
    } catch (e: FileNotFoundException) {
        println("An error occurred.")
        e.printStackTrace()
    }
    varios.removeAt(0)
    return varios
}

fun leer_receta_medica(archivo_receta_medica:String): ArrayList<Receta_Medica> {
    val recetas_medicas = ArrayList<Receta_Medica>()
    val receta_lista = leer_archivo(archivo_receta_medica)
    receta_lista.forEach(){
        val fecha = SimpleDateFormat("dd/mm/yyyy").parse(it[1])
        recetas_medicas.add(Receta_Medica(it[0].toInt(), fecha, it[2].toString(), it[3].toInt(), it[4].toString(), it[5].toString()))
    }
    return recetas_medicas
}

fun leer_medicamento(archivo_medicamentos:String): ArrayList<Medicamento> {
    val medicamentos = ArrayList<Medicamento>()
    val medicamento_lista = leer_archivo(archivo_medicamentos)
    medicamento_lista.forEach(){
        medicamentos.add(Medicamento(it[0].toInt(),it[1].toString(),it[2].toDouble(),it[3].toString(), it[4].toString(), it[5].toBoolean()))
    }
    return medicamentos
}

//FUNCIONES PARA ACTUALIZAR
fun actualizar_receta_medica(lista_recetas_medicas: ArrayList<Receta_Medica>, receta_medica_archivo: String ) {
    val scanner = Scanner(System.`in`)
    var identificador:String?
    var fecha:String?
    var nombre:String?
    var edad:String?
    var diagnostico:String?
    var frecuencia_duracion_tratamiento:String?
    print("Las recetas medicas registradas son las siguientes: \n")
    imprimir_receta_medica(lista_recetas_medicas)
    print("Ingrese el identificador de la receta medica a actualizar: \n")
    val entrada_identificador = scanner.nextLine()
    print("Seleccione el campo que desea actualizar: \n" +
            "1. Nombre\n" +
            "2. Edad\n" +
            "3. Diagnostico\n" +
            "4. Frecuencia y duracion del tratamiento\n" +
            "Opción: "
    )

    val seleccion_campo = scanner.nextLine().toInt()
    print("Ingrese la actualizacion que desea realizar: \n")
    val actualizacion = scanner.nextLine()
    var campo_antiguo:String = ""

    if (seleccion_campo.toString() == "1"){
        lista_recetas_medicas.filter { it.getIdentificador() == entrada_identificador.toInt() }.forEach {
            campo_antiguo = it.getNombre().toString()
            it.setNombre(actualizacion.toString())
        }
    }

    if (seleccion_campo.toString() == "2"){
        lista_recetas_medicas.filter { it.getIdentificador() == entrada_identificador.toInt() }.forEach {
            campo_antiguo = it.getEdad().toString()
            it.setEdad(actualizacion.toInt())
        }
    }

    if (seleccion_campo.toString() == "3"){
        lista_recetas_medicas.filter { it.getIdentificador() == entrada_identificador.toInt() }.forEach {
            campo_antiguo = it.getDiagnostico().toString()
            it.setDiagnostico(actualizacion.toString())
        }
    }

    if (seleccion_campo.toString() == "4"){
        lista_recetas_medicas.filter { it.getIdentificador() == entrada_identificador.toInt() }.forEach {
            campo_antiguo = it.getFrecuencia_duracion_tratamiento().toString()
            it.setFrecuencia_duracion_tratamiento(actualizacion.toString())
        }
    }

    imprimir_receta_medica(lista_recetas_medicas)

    val borrar = encontrar_receta_medica(0, entrada_identificador, lista_recetas_medicas)
    borrar.forEach{
        identificador = it.getIdentificador().toString()
        val formato = SimpleDateFormat("dd/mm/yyyy")
        val fecha: String? = formato.format(it.getFecha())
        nombre = it.getNombre()
        edad = it.getEdad().toString()
        diagnostico = it.getDiagnostico()
        frecuencia_duracion_tratamiento = it.getFrecuencia_duracion_tratamiento()

        when (entrada_identificador.toString()) {
            "1" -> {
                borrar_fila(receta_medica_archivo, "${identificador},${fecha},${campo_antiguo},${edad},${diagnostico},${frecuencia_duracion_tratamiento},")
            }
            "2" -> {
                borrar_fila(receta_medica_archivo, "${identificador},${fecha},${nombre},${campo_antiguo},${diagnostico},${frecuencia_duracion_tratamiento},")
            }
            "3" -> {
                borrar_fila(receta_medica_archivo, "${identificador},${fecha},${nombre},${edad},${campo_antiguo},${frecuencia_duracion_tratamiento},")
            }
            "4" -> {
                borrar_fila(receta_medica_archivo, "${identificador},${fecha},${nombre},${edad},${diagnostico},${campo_antiguo},")
            }
        }
        try {


            FileWriter(receta_medica_archivo, true).use { fw ->
                BufferedWriter(fw).use { bw ->
                    PrintWriter(bw).use { out ->
                        out.print("\n"+"${identificador},${fecha},${nombre},${edad},${diagnostico},${frecuencia_duracion_tratamiento},")
                    }
                }
            }
        } catch (e: IOException) {
            //exception handling left as an exercise for the reader
        }

    }
    borrar_fila(receta_medica_archivo,"")
}

fun actualizar_medicamento(lista_medicamentos: ArrayList<Medicamento>, medicamento_archivo: String ) {
    val scanner = Scanner(System.`in`)
    var identificador:String?
    var nombre:String?
    var concentracion:String?
    var via_administracion:String?
    var forma_farmaceutica:String?
    var venta_libre:String?
    print("Las medicamentos registrados son los siguientes: \n")
    imprimir_medicamento(lista_medicamentos)
    print("Ingrese el identificador del medicamento a actualizar: \n")
    val entrada_identificador = scanner.nextLine()
    print("Seleccione el campo que desea actualizar: \n" +
            "1. Nombre del medicamento\n" +
            "2. Concentración del medicamento\n" +
            "3. Via de administracion del medicamento\n" +
            "4. Forma farmaceutica del medicamento\n" +
            "5. Venta libre del medicamento\n" +
            "En caso de ser medicamento de venta libre escriba 'true' caso contrario 'false'\n" +
            "Opción: "
    )

    val seleccion_campo = scanner.nextLine().toInt()
    print("Ingrese la actualizacion que desea realizar: \n")
    val actualizacion = scanner.nextLine()
    var campo_antiguo:String = ""

    if (seleccion_campo.toString() == "1"){
        lista_medicamentos.filter { it.getIdentificador() == entrada_identificador.toInt() }.forEach {
            campo_antiguo = it.getNombre().toString()
            it.setNombre(actualizacion.toString())
        }
    }

    if (seleccion_campo.toString() == "2"){
        lista_medicamentos.filter { it.getIdentificador() == entrada_identificador.toInt() }.forEach {
            campo_antiguo = it.getConcentracion().toString()
            it.setConcentracion(actualizacion.toDouble())
        }
    }

    if (seleccion_campo.toString() == "3"){
        lista_medicamentos.filter { it.getIdentificador() == entrada_identificador.toInt() }.forEach {
            campo_antiguo = it.getVia_administracion().toString()
            it.setVia_administracion(actualizacion.toString())
        }
    }

    if (seleccion_campo.toString() == "4"){
        lista_medicamentos.filter { it.getIdentificador() == entrada_identificador.toInt() }.forEach {
            campo_antiguo = it.getForma_farmaceutica().toString()
            it.setForma_farmaceutica(actualizacion.toString())
        }
    }

    if (seleccion_campo.toString() == "5"){
        lista_medicamentos.filter { it.getIdentificador() == entrada_identificador.toInt() }.forEach {
            campo_antiguo = it.getVenta_libre().toString()
            it.setVenta_libre(actualizacion.toBoolean())
        }
    }

    imprimir_medicamento(lista_medicamentos)

    val borrar = encontrar_medicamento(0, entrada_identificador, lista_medicamentos)
    borrar.forEach{
        identificador = it.getIdentificador().toString()
        nombre = it.getNombre()
        concentracion = it.getConcentracion().toString()
        via_administracion = it.getVia_administracion()
        forma_farmaceutica = it.getForma_farmaceutica()
        venta_libre = it.getVenta_libre().toString()

        when (entrada_identificador.toString()) {
            "1" -> {
                borrar_fila(medicamento_archivo, "${identificador},${campo_antiguo},${concentracion},${via_administracion},${forma_farmaceutica},${venta_libre},")
            }
            "2" -> {
                borrar_fila(medicamento_archivo, "${identificador},${nombre},${campo_antiguo},${via_administracion},${forma_farmaceutica},${venta_libre},")
            }
            "3" -> {
                borrar_fila(medicamento_archivo, "${identificador},${nombre},${concentracion},${campo_antiguo},${forma_farmaceutica},${venta_libre},")
            }
            "4" -> {
                borrar_fila(medicamento_archivo, "${identificador},${nombre},${concentracion},${via_administracion},${campo_antiguo},${venta_libre},")
            }
            "5" -> {
                borrar_fila(medicamento_archivo, "${identificador},${nombre},${concentracion},${via_administracion},${forma_farmaceutica},${campo_antiguo},")
            }
        }
        try {
            FileWriter(medicamento_archivo, true).use { fw ->
                BufferedWriter(fw).use { bw ->
                    PrintWriter(bw).use { out ->
                        out.print("\n"+"${identificador},${nombre},${concentracion},${via_administracion},${forma_farmaceutica},${venta_libre},")
                    }
                }
            }
        } catch (e: IOException) {
            //exception handling left as an exercise for the reader
        }

    }
    borrar_fila(medicamento_archivo,"")
}







//FUNCIONES PARA ENCONTRAR
fun encontrar_receta_medica( columna: Int, str:String, archivo: ArrayList<Receta_Medica> ): ArrayList<Receta_Medica> {

    if(columna == 1){
        return (archivo.filter {
            return@filter (it.getIdentificador() == str.toInt())
        } as ArrayList<Receta_Medica>)
    }

    if(columna == 2){
        val fecha = SimpleDateFormat("dd/mm/yyyy").parse(str)
        return (archivo.filter {
            return@filter (it.getFecha() == fecha)
        } as ArrayList<Receta_Medica>)
    }

    if(columna == 3 ){
        return (archivo.filter {
            return@filter (it.getNombre() == str.toString())
        } as ArrayList<Receta_Medica>)

    }
    if(columna == 4 ){
        return (archivo.filter {
            return@filter (it.getEdad() == str.toInt())
        } as ArrayList<Receta_Medica>)

    }

    if(columna == 5 ){
        return (archivo.filter {
            return@filter (it.getDiagnostico() == str.toString())
        } as ArrayList<Receta_Medica>)

    }

    return (archivo.filter {
        return@filter (it.getFrecuencia_duracion_tratamiento() == str.toString())
    } as ArrayList<Receta_Medica>)

}

fun encontrar_medicamento( columna: Int, str:String, archivo: ArrayList<Medicamento> ): ArrayList<Medicamento> {

    if(columna == 0){
        return (archivo.filter {
            return@filter (it.getIdentificador() == str.toInt())
        } as ArrayList<Medicamento>)
    }

    if(columna == 1){
        return (archivo.filter {
            return@filter (it.getNombre() == str.toString())
        } as ArrayList<Medicamento>)
    }

    if(columna ==2 ){
        return (archivo.filter {
            return@filter (it.getConcentracion() == str.toDouble())
        } as ArrayList<Medicamento>)

    }
    if(columna ==3 ){
        return (archivo.filter {
            return@filter (it.getVia_administracion() == str.toString())
        } as ArrayList<Medicamento>)

    }

    if(columna ==4 ){
        return (archivo.filter {
            return@filter (it.getForma_farmaceutica() == str.toString())
        } as ArrayList<Medicamento>)

    }

    return (archivo.filter {
        return@filter (it.getVenta_libre() == str.toBoolean())
    } as ArrayList<Medicamento>)

}

//FUNCION PARA BORRAR LA FILA SELECCIONADA DEL ARCHIVO
fun borrar_fila(fila: String?, fila_para_remover: String) {
    try {
        val inFile = File(fila)
        if (!inFile.isFile) {
            println("Parameter is not an existing file")
            return
        }

        //Construye el nuevo archivo que luego será renombrado al nombre de archivo original.
        val archivo_temporal = File(inFile.absolutePath + ".tmp")
        val br = BufferedReader(FileReader(fila))
        val pw = PrintWriter(FileWriter(archivo_temporal))
        var line: String? = null
        //Se lee del archivo original y se escribe en el nuevo archivo solo si existen coincidencia con los datos de vamos a eliminar

        while (br.readLine().also { line = it } != null) {
            if (line!!.trim { it <= ' ' } != fila_para_remover) {
                pw.println(line)
                pw.flush()
            }
        }
        pw.close()
        br.close()

        //Aquí se borra el archivo original
        if (!inFile.delete()) {
            println("Could not delete file")
            return
        }

        //Cambie el nombre del nuevo archivo por el nombre de archivo que tenía el archivo original.
        if (!archivo_temporal.renameTo(inFile)) println("Could not rename file")
    } catch (ex: FileNotFoundException) {
        ex.printStackTrace()
    } catch (ex: IOException) {
        ex.printStackTrace()
    }
}

//FUNCIONES PARA IMPRIMIR EN PANTALLA
fun imprimir_receta_medica(recetas_medicas: ArrayList<Receta_Medica>) {

    System.out.format("%-15s%-12s%-21s%-6s%-31s%-81s\n", "Identificador","Fecha","Nombre del paciente","Edad", "Diagnostico","Frecuencia y duracion del tratamiento")
    recetas_medicas.forEach {
        val formato = SimpleDateFormat("dd/mm/yyyy")
        val fecha1: String? = formato.format(it.getFecha())
        System.out.format("%-15s%-12s%-21s%-6s%-31s%-81s\n", it.getIdentificador(), fecha1, it.getNombre(), it.getEdad(), it.getDiagnostico(), it.getFrecuencia_duracion_tratamiento() )
    }
}

fun imprimir_medicamento(medicamentos: ArrayList<Medicamento>) {
    System.out.format("%-15s%-24s%-15s%-23s%-20s%-27s\n", "Identificador","Nombre del medicamento","Concentracion","Via de administracion", "Forma farmaceutica","Venta libre del medicamento")
    medicamentos.forEach{
        System.out.format("%-15s%-24s%-15s%-23s%-20s%-27s\n", it.getIdentificador(), it.getNombre(), it.getConcentracion(), it.getVia_administracion(), it.getForma_farmaceutica(), it.getVenta_libre())
    }
}




