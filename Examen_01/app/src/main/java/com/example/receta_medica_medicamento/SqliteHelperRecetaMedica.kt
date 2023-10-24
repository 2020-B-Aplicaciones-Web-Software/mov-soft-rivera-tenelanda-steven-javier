package com.example.receta_medica_medicamento

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteHelperRecetaMedica (
    contexto: Context
): SQLiteOpenHelper(
    contexto,
    "moviles1",
    null,
    2
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaRecetaMedica =
            """
                CREATE TABLE RECETAMEDICA(
                    id_receta_medica INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(50),
                    edad INTEGER,
                    diagnostico VARCHAR(50),
                    frecuencia_duracion_tratamiento VARCHAR(100)
                )
            """.trimIndent()
        db?.execSQL(scriptCrearTablaRecetaMedica)

        val scriptCrearTablaMedicamento =
            """
                CREATE TABLE MEDICAMENTO(
                    id_medicamento INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre_medicamento VARCHAR(50),
                    concentracion DOUBLE,
                    forma_farmaceutica VARCHAR(100),
                    venta_libre VARCHAR(10),
                    id_receta_medica INTEGER
                )
            """.trimIndent()
        db?.execSQL(scriptCrearTablaMedicamento)
    }

    //FUNCIONES PARA CREAR
    fun crearRecetaMedicaFormulario(
        nombre: String,
        edad: Int,
        diagnostico: String,
        frecuencia_duracion_tratamiento: String
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("edad", edad)
        valoresAGuardar.put("diagnostico", diagnostico)
        valoresAGuardar.put("frecuencia_duracion_tratamiento", frecuencia_duracion_tratamiento)
        val resultadoEscritura: Long = conexionEscritura
            .insert(
                "RECETAMEDICA",
                null,
                valoresAGuardar
            )
        conexionEscritura.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }

    fun crearMedicamentoFormulario(
        nombre_medicamento: String,
        concentracion: Double,
        forma_farmaceutica: String,
        venta_libre: Boolean,
        id_receta_medica: Int,
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre_medicamento", nombre_medicamento)
        valoresAGuardar.put("concentracion", concentracion)
        valoresAGuardar.put("forma_farmaceutica", forma_farmaceutica)
        valoresAGuardar.put("venta_libre", venta_libre.toString())
        valoresAGuardar.put("id_receta_medica", id_receta_medica)
        val resultadoEscritura: Long = conexionEscritura
            .insert(
                "MEDICAMENTO",
                null,
                valoresAGuardar
            )
        conexionEscritura.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }

    //FUNCIONES PARA CONSULTAR
    fun consultarRecetasMedicas(): ArrayList<RecetaMedica> {
        val scriptConsultarRecetaMedica = "SELECT * FROM RECETAMEDICA"
        val baseDeDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDeDatosLectura.rawQuery(
            scriptConsultarRecetaMedica,
            null
        )
        val existeRecetaMedica = resultadoConsultaLectura.moveToFirst()
        val arregloRecetaMedica = arrayListOf<RecetaMedica>()
        if (existeRecetaMedica) {
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val edad = resultadoConsultaLectura.getInt(2)
                val diagnostico = resultadoConsultaLectura.getString(3)
                val frecuencia_duracion_tratamiento = resultadoConsultaLectura.getString(4)
                arregloRecetaMedica.add(RecetaMedica(id,nombre,edad,diagnostico,frecuencia_duracion_tratamiento,null))
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDeDatosLectura.close()
        return arregloRecetaMedica
    }

    fun consultarMedicamentos(id_receta: Int?): ArrayList<Medicamento> {
        val scriptConsultarMedicamentos = "SELECT * FROM MEDICAMENTO WHERE ID_RECETA_MEDICA = ${id_receta}"
        val baseDeDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDeDatosLectura.rawQuery(
            scriptConsultarMedicamentos,
            null
        )
        val existeMedicamento = resultadoConsultaLectura.moveToFirst()
        val arregloMedicamentos = arrayListOf<Medicamento>()
        if (existeMedicamento) {
            do {
                val id_medicamento = resultadoConsultaLectura.getInt(0)
                val nombre_medicamento = resultadoConsultaLectura.getString(1)
                val concentracion = resultadoConsultaLectura.getDouble(2)
                val forma_farmaceutica = resultadoConsultaLectura.getString(3)
                val venta_libre = resultadoConsultaLectura.getString(4)
                val id_receta_medica = resultadoConsultaLectura.getInt(5)
                arregloMedicamentos.add(Medicamento(id_medicamento,nombre_medicamento,concentracion,forma_farmaceutica,venta_libre.toBoolean(),id_receta_medica))
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDeDatosLectura.close()
        return arregloMedicamentos
    }

    //FUNCIONES PARA ELIMINAR
    fun eliminarRecetaMedicaFormulario(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "RECETAMEDICA",
                "id_receta_medica=?",
                arrayOf(
                    id.toString()
                )
            )
        return if (resultadoEliminacion == -1) false else true
    }

    fun eliminarMedicamentoFormulario(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "MEDICAMENTO",
                "id_medicamento=?",
                arrayOf(
                    id.toString()
                )
            )
        return if (resultadoEliminacion == -1) false else true
    }

    //FUNCIONES PARA ACTUALIZAR
    fun actualizarRecetaMedicaFormulario(
        id: Int,
        nombre: String,
        edad: Int,
        diagnostico: String,
        frecuencia_duracion_tratamiento: String,
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("edad", edad)
        valoresAActualizar.put("diagnostico", diagnostico)
        valoresAActualizar.put("frecuencia_duracion_tratamiento", frecuencia_duracion_tratamiento)

        val resultadoActualizacion = conexionEscritura
            .update(
                "RECETAMEDICA",
                valoresAActualizar,
                "id_receta_medica=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }

    fun actualizarMedicamentoFormulario(
        id: Int,
        nombre_medicamento: String,
        concentracion: Double,
        forma_farmaceutica: String,
        venta_libre: Boolean,
        id_receta_medica: Int,

    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre_medicamento", nombre_medicamento)
        valoresAActualizar.put("concentracion", concentracion)
        valoresAActualizar.put("forma_farmaceutica", forma_farmaceutica)
        valoresAActualizar.put("venta_libre", venta_libre.toString())
        valoresAActualizar.put("id_receta_medica", id_receta_medica)

        val resultadoActualizacion = conexionEscritura
            .update(
                "MEDICAMENTO",
                valoresAActualizar,
                "id_medicamento=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


}