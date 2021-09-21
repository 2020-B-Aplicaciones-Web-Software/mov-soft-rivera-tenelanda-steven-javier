package com.example.examen_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearRecetaMedica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_receta_medica)

        val boton_crear_recetas_medicas = findViewById<Button>(
            R.id.btn_crear_receta_medica_formulario
        )

        boton_crear_recetas_medicas.setOnClickListener {
            crearRecetaMedica()
        }
    }

    fun crearRecetaMedica(){
        val nombre = findViewById<TextInputEditText>(R.id.input_nombre_paciente).text
        val edad = findViewById<TextInputEditText>(R.id.input_edad).text
        val diagnostico = findViewById<TextInputEditText>(R.id.input_diagnostico).text
        val frecuencia_duracion_tratamiento = findViewById<TextInputEditText>(R.id.input_frecuencia_duracion).text

        val toast = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)
        if ((nombre.toString() == "") or(edad.toString() == "") or (diagnostico.toString() == "") or (frecuencia_duracion_tratamiento.toString() == "")){
            toast.setText("Ingrese todos los campos")
            toast.show()
        } else {
            val nuevaRecetaMedica = hashMapOf<String, Any>(
                "nombre_paciente" to nombre.toString(),
                "edad" to edad.toString().toInt(),
                "diagnostico" to diagnostico.toString(),
                "frecuencia_duracion_tratamiento" to frecuencia_duracion_tratamiento.toString()
            )

            val db = Firebase.firestore
            val referencia = db.collection("receta_medica")

            referencia
                .add(nuevaRecetaMedica)
                .addOnSuccessListener {
                    nombre?.clear()
                    edad?.clear()
                    diagnostico?.clear()
                    frecuencia_duracion_tratamiento?.clear()
                    toast.setText("Receta Medica creada exitosamente")
                    toast.show()
                }
                .addOnFailureListener {  }

        }
    }
}