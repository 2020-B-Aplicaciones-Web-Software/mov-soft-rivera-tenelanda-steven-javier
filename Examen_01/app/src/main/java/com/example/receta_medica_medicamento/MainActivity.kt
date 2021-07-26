package com.example.receta_medica_medicamento

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //boton iniciar Receta Medica
        val botonIrRecetaMedica = findViewById<Button>(
            R.id.btn_iniciar
        )
        botonIrRecetaMedica
            .setOnClickListener{
                val intentExplicito = Intent(
                    this,
                    VistaRecetaMedica::class.java
                )
                startActivity(intentExplicito)
            }
    }
}