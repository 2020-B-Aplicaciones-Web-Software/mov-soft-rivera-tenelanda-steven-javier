package com.example.deber_02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonIrSteamChat = findViewById<Button>(R.id.btn_inicio)
        botonIrSteamChat
            .setOnClickListener {
                val intentExplicito = Intent(
                   this,
                   VistaAmigosSteam::class.java
                )
                startActivity(intentExplicito)
            }
    }

}

