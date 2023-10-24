package com.example.trabajo_clase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonCrearPaciente = findViewById<Button>(
            //Buscamos el identificador del boton
            R.id.btn_crear_paciente
        )
        botonCrearPaciente
            .setOnClickListener{
            //funcion a llamar cuando se presione el boton
                crearPaciente(
                    Paciente::class.java
                )
        }
    }

    fun crearPaciente(
        clase : Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }
}