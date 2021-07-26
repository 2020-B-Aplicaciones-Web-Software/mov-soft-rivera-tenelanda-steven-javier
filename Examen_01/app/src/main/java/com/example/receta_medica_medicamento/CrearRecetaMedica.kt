package com.example.receta_medica_medicamento

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class CrearRecetaMedica : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_receta_medica)
        val boton_crear_recetas_medicas = findViewById<Button>(
            R.id.btn_crear_receta_medica_formulario
        )
        boton_crear_recetas_medicas.setOnClickListener{
                val nombre = findViewById<TextInputEditText>(R.id.input_nombre_paciente).text
                val edad = findViewById<TextInputEditText>(R.id.input_edad).text
                val diagnostico = findViewById<TextInputEditText>(R.id.input_diagnostico).text
                val frecuencia_duracion_tratamiento = findViewById<TextInputEditText>(R.id.input_frecuencia_duracion).text

                val toast = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)
                if ((nombre.toString() == "") or(edad.toString() == "") or (diagnostico.toString() == "") or (frecuencia_duracion_tratamiento.toString() == "")){
                    toast.setText("Ingrese todos los campos")
                    toast.show()
                } else {
                    val receta_medica = SqliteHelperRecetaMedica(this)
                    if (receta_medica.crearRecetaMedicaFormulario(nombre.toString(), edad.toString().toInt(), diagnostico.toString(), frecuencia_duracion_tratamiento.toString())) {
                        findViewById<TextInputEditText>(R.id.input_nombre_paciente).setText("")
                        findViewById<TextInputEditText>(R.id.input_edad).setText("")
                        findViewById<TextInputEditText>(R.id.input_diagnostico).setText("")
                        findViewById<TextInputEditText>(R.id.input_frecuencia_duracion).setText("")
                        toast.setText("Receta Medica creada exitosamente")
                        toast.show()
                    }
                }
            }
    }
}