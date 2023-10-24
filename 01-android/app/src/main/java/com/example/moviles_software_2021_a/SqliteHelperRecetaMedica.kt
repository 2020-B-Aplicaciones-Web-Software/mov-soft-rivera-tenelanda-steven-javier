package com.example.moviles_software_2021_a

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteHelperRecetaMedica (
    contexto: Context
): SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaRecetaMedica =
            """
                CREATE TABLE RECETAMEDICA(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(50),
                    diagnostico VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptCrearTablaRecetaMedica)
    }

    fun crearRecetaMedicaFormulario(
        nombre: String,
        diagnostico: String
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("diagnostico", diagnostico)
        val resultadoEscritura: Long = conexionEscritura
            .insert(
                "RECETAMEDICA",
                null,
                valoresAGuardar
            )
        conexionEscritura.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }

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
                val diagnostico = resultadoConsultaLectura.getString(2)
                arregloRecetaMedica.add(RecetaMedica(id, nombre, diagnostico))
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDeDatosLectura.close()
        return arregloRecetaMedica
    }

    fun consultarRecetaMedicaPorId(id: Int): RecetaMedica {
        val scripConsultarRecetaMedica = "SELECT * FROM RECETAMEDICA WHERE ID = ${id}"
        val baseDeDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDeDatosLectura.rawQuery(
            scripConsultarRecetaMedica,
            null
        )
        val existeRecetaMedica = resultadoConsultaLectura.moveToFirst()
        val recetamedicaEncontrada = RecetaMedica(0, "", "")
        if (existeRecetaMedica) {
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val diagnostico = resultadoConsultaLectura.getString(2)
                recetamedicaEncontrada.receta_id = id
                recetamedicaEncontrada.nombre = nombre
                recetamedicaEncontrada.diagnostico = diagnostico
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDeDatosLectura.close()
        return recetamedicaEncontrada
    }

    fun eliminarRecetaMedicaFormulario(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "RECETAMEDICA",
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        return if (resultadoEliminacion == -1) false else true
    }

    fun actualizarRecetaMedicaFormulario(
        id: Int,
        nombre: String,
        diagnostico: String
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("diagnostico", diagnostico)

        val resultadoActualizacion = conexionEscritura
            .update(
                "RECETAMEDICA",
                valoresAActualizar,
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}