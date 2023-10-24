package com.example.moviles_software_2021_a

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)

        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getIntExtra("edad", 0)

        Log.i("intent-explicito", "${nombre}")
        Log.i("intent-explicito", "${apellido}")
        Log.i("intent-explicito", "${edad}")

        val botonDevolverRespuesta = findViewById<Button>(R.id.btn_devolver_respuesta)

        botonDevolverRespuesta
            .setOnClickListener {
                val intentDevolverParametros = Intent()
                intentDevolverParametros.putExtra("nombreModificado", "Vicente")
                intentDevolverParametros.putExtra("edadModificado", 33)
                    //this.setResult()
                //setResult(Activity.RESULT_OK)
                setResult(RESULT_OK, intentDevolverParametros)
                //this.finish()
                finish()
            }
    }
}
