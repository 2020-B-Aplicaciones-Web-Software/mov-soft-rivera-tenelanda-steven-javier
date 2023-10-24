package com.example.moviles_software_2021_a

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
            findViewById<TextInputEditText>(R.id.texto_nombre12).setText(receta_medica.nombre)
            findViewById<TextInputEditText>(R.id.texto_diagnostico).setText(receta_medica.diagnostico)
        }
        val boton_actualizar_receta_medica = findViewById<Button>(
            R.id.btn_actualizar
        )
        boton_actualizar_receta_medica.setOnClickListener {
            val nombre = findViewById<TextInputEditText>(R.id.texto_nombre12).text
            val diagnostico = findViewById<TextInputEditText>(R.id.texto_diagnostico).text
            val toast = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)
            if ((nombre.toString() == "") or (diagnostico.toString() == "")) {
                toast.setText("Ingrese todos los campos")
                toast.show()
            } else {
                val RecetaMedicaBD = SqliteHelperRecetaMedica(this)
                if (receta_medica != null) {
                    if(RecetaMedicaBD.actualizarRecetaMedicaFormulario(
                      receta_medica.receta_id,
                      nombre.toString(),
                      diagnostico.toString()
                    )
                    ){
                        findViewById<TextInputEditText>(R.id.texto_nombre12).setText("")
                        findViewById<TextInputEditText>(R.id.texto_diagnostico).setText("")
                        toast.setText("La receta medica se actualizo correctamente")
                        toast.show()
                    }
                }
            }
        }
    }
}