package com.example.trabajo_clase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Paciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paciente)

        //Se registra el menu que se creo
        registerForContextMenu()
    }
}