package com.example.moviles_software_2021_a

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.core.content.contentValuesOf

class ESqliteHelperUsuario (
    contexto: Context?
): SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaUsuario =
            """
            CREATE TABLE USUARIO (
                ID INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion varchar(50)
            )   
            """.trimIndent()
            Log.i("bbd", "Creando la tabla usuario")
            db?.execSQL(scriptCrearTablaUsuario)
    }

    fun crearUsuarioFormulario(
        nombre: String,
        descripcion: String
    ):Boolean{
        //val conexionEscritura = this.writableDatabase
        val conexionEscritura = writableDatabase

        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)

        val resultadoEscritura: Long = conexionEscritura
            .insert(
                "USUARIO",
                null,
                valoresAGuardar
            )
        conexionEscritura.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }

    fun  consultarUsuarioPorId(id: Int): EUsuarioBDD {
        val scriptConsultarUsuario = "SELECT * FROM USUARIO WHERE ID = ${id}"

        val baseDatosLectura = readableDatabase

        val resultaConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )
    }



    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
