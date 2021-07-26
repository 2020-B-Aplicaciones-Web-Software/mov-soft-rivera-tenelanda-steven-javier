package com.example.moviles_software_2021_a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class FormularioRecetaMedica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_receta_medica)
        val btn_anadir_recetas_medicas = findViewById<Button>(
            R.id.btn_anadir_receta_medica1
        )
        btn_anadir_recetas_medicas.setOnClickListener {
            val nombre = findViewById<TextInputEditText>(R.id.texto_paciente).text
            val diagnostico = findViewById<TextInputEditText>(R.id.texto_diagnostico).text
            val toast = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)
            if ((nombre.toString() == "") or (diagnostico.toString() == "")){
                toast.setText("Ingrese todos los campos")
                toast.show()
            } else {
                val receta_medica = SqliteHelperRecetaMedica(this)
                if (receta_medica.crearRecetaMedicaFormulario(nombre.toString(), diagnostico.toString())) {
                    findViewById<TextInputEditText>(R.id.texto_paciente).setText("")
                    findViewById<TextInputEditText>(R.id.texto_diagnostico).setText("")
                    toast.setText("Receta Medica creada exitosamente")
                    toast.show()
                }
            }
        }

    }
}