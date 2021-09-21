package com.example.examen_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ActualizarRecetaMedica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_receta_medica)

        val receta_medica = intent.getParcelableExtra<RecetaMedica>("RECETAMEDICA")

        if (receta_medica != null) {
            findViewById<TextInputEditText>(R.id.input_actu_nombre_paciente).setText(receta_medica.nombre_paciente)
            findViewById<TextInputEditText>(R.id.input_actu_edad).setText(receta_medica.edad.toString())
            findViewById<TextInputEditText>(R.id.input_actu_diagnostico).setText(receta_medica.diagnostico)
            findViewById<TextInputEditText>(R.id.input_actu_frecuencia_duracion).setText(receta_medica.frecuencia_duracion_tratamiento)
        }

        val boton_actualizar_receta_medica = findViewById<Button>(
            R.id.btn_actualizar_receta_medica_formulario
        )

        boton_actualizar_receta_medica.setOnClickListener {
            val nombre = findViewById<TextInputEditText>(R.id.input_actu_nombre_paciente).text.toString()
            val edad = findViewById<TextInputEditText>(R.id.input_actu_edad).text.toString().toInt()
            val diagnostico = findViewById<TextInputEditText>(R.id.input_actu_diagnostico).text.toString()
            val frecuencia_duracion_tratamiento = findViewById<TextInputEditText>(R.id.input_actu_frecuencia_duracion).text.toString()


            val toast = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)
            if ((nombre == "") or(edad.toString() == "") or (diagnostico == "") or (frecuencia_duracion_tratamiento == "")){
                toast.setText("Ingrese todos los campos")
                toast.show()
            } else {

                val db = Firebase.firestore
                val referenciaRecetaMedica = db
                    .collection("receta_medica")
                    .whereEqualTo("nombre_paciente", receta_medica?.nombre_paciente)
                    .whereEqualTo("edad", receta_medica?.edad)
                    .whereEqualTo("diagnostico", receta_medica?.diagnostico)
                    .whereEqualTo("frecuencia_duracion_tratamiento", receta_medica?.frecuencia_duracion_tratamiento)

                referenciaRecetaMedica
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result){
                            val referencia_documento = db.collection("receta_medica").document(document.id)
                            referencia_documento.update("nombre_paciente", nombre)
                            referencia_documento.update("edad", edad)
                            referencia_documento.update("diagnostico", diagnostico)
                            referencia_documento.update("frecuencia_duracion_tratamiento", frecuencia_duracion_tratamiento)
                        }
                        findViewById<TextInputEditText>(R.id.input_actu_nombre_paciente).setText("")
                        findViewById<TextInputEditText>(R.id.input_actu_edad).setText("")
                        findViewById<TextInputEditText>(R.id.input_actu_diagnostico).setText("")
                        findViewById<TextInputEditText>(R.id.input_actu_frecuencia_duracion).setText("")
                        toast.setText("La receta medica se actualizo correctamente")
                        toast.show()
                    }.addOnFailureListener {  }

            }
        }
    }
}