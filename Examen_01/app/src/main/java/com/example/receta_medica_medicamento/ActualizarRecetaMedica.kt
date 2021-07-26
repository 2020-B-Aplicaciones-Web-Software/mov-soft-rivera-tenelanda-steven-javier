package com.example.receta_medica_medicamento

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

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
        boton_actualizar_receta_medica.setOnClickListener{
            val nombre = findViewById<TextInputEditText>(R.id.input_actu_nombre_paciente).text
            val edad = findViewById<TextInputEditText>(R.id.input_actu_edad).text
            val diagnostico = findViewById<TextInputEditText>(R.id.input_actu_diagnostico).text
            val frecuencia_duracion_tratamiento = findViewById<TextInputEditText>(R.id.input_actu_frecuencia_duracion).text

            val toast = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)
            if ((nombre.toString() == "") or(edad.toString() == "") or (diagnostico.toString() == "") or (frecuencia_duracion_tratamiento.toString() == "")){
                toast.setText("Ingrese todos los campos")
                toast.show()
            } else {
                val RecetaMedicaBD = SqliteHelperRecetaMedica(this)
                if (receta_medica != null) {
                    if (RecetaMedicaBD.actualizarRecetaMedicaFormulario(
                        receta_medica.id_receta,
                        nombre.toString(),
                        edad.toString().toInt(),
                        diagnostico.toString(),
                        frecuencia_duracion_tratamiento.toString(),
                    )
                    ){
                        findViewById<TextInputEditText>(R.id.input_actu_nombre_paciente).setText("")
                        findViewById<TextInputEditText>(R.id.input_actu_edad).setText("")
                        findViewById<TextInputEditText>(R.id.input_actu_diagnostico).setText("")
                        findViewById<TextInputEditText>(R.id.input_actu_frecuencia_duracion).setText("")
                        toast.setText("La receta medica se actualizo correctamente")
                        toast.show()
                    }
                }
            }
        }
    }
}