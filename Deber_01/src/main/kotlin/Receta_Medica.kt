import java.util.*

class Receta_Medica (
        private var identificador: Int,
        private var fecha: Date,
        private var nombre: String,
        private var edad: Int,
        private var diagnostico: String,
        private var frecuencia_duracion_tratamiento: String,
){
    init {

    }

    //GETTERS
    fun getIdentificador(): Int {
        return identificador
    }

    fun getFecha(): Date {
        return fecha
    }

    fun getNombre(): String {
        return nombre
    }

    fun  getEdad(): Int {
        return edad
    }

    fun  getDiagnostico(): String {
        return diagnostico
    }

    fun  getFrecuencia_duracion_tratamiento(): String {
        return frecuencia_duracion_tratamiento
    }

    //SETTERS

    fun setIdentificador(identificador: Int) {
        this.identificador = identificador
    }

    fun setFecha(fecha: Date) {
        this.fecha = fecha
    }

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }

    fun setEdad(edad: Int) {
        this.edad = edad
    }

    fun setDiagnostico(diagnostico: String) {
        this.diagnostico = diagnostico
    }

    fun setFrecuencia_duracion_tratamiento(frecuencia_duracion_tratamiento: String) {
        this.frecuencia_duracion_tratamiento = frecuencia_duracion_tratamiento
    }


}