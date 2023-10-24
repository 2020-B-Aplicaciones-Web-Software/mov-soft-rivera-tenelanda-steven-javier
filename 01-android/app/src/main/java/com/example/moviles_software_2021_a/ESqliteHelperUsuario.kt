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

        val existeUsuario = resultaConsultaLectura.moveToFirst()

        //CREAMOS UNA INSTANCIA PARA MAPEAR A LA BASE DE DATOS
        val usuarioEncontrado = EUsuarioBDD(0,"","")
        //val arregloUsuarios = arrayListOf<EUsuarioBDD>()
        if (existeUsuario){

            do {
                val id = resultaConsultaLectura.getInt(0) //Columna indice 0 -> ID
                val nombre = resultaConsultaLectura.getString(1) //Columna indice 1 -> Nombre
                val descripcion = resultaConsultaLectura.getString(2) //Columna indice 2 ->DESCRIPCION

                if (id != null){
                    //arregloUsuarios.add(
                    //    EUsuarioBDD(id, nombre, descripcion)
                    //)
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                }

            } while (resultaConsultaLectura.moveToNext())
        }
        resultaConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }

    fun eliminarUsuarioFormulario(id: Int): Boolean {

        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "USUARIO", //Tabla
                "id=?", //Clausula Where
                arrayOf(
                    id.toString(),
                )//Arreglo ordenado de parametros
            )
        conexionEscritura.close()
        return if (resultadoEliminacion == -1) false else true
    }

    fun actualizarUsuarioFormulario(
        nombre: String,
        descripcion: String,
        idActualizar: Int
    ): Boolean {
        val conexionEscritura = writableDatabase

        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)

        val resultadoActualizacion = conexionEscritura
            .update(
                "USUARIO", //Nombre tabla
                valoresAActualizar, //Valores a actualizar
                "id=?", //Clausula Where
                arrayOf(
                    idActualizar.toString()
                )//Parametros clausula Where
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }





    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
