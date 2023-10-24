package com.example.receta_medica_medicamento

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class ActualizarMedicamento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_medicamento)

        val medicamento = intent.getParcelableExtra<Medicamento>("MEDICAMENTO")
        val receta_medica_seleccionada = intent.getParcelableExtra<RecetaMedica>("RECETA_MEDICA_SELECCIONADA")
        if (medicamento != null) {
            findViewById<TextInputEditText>(R.id.input_actu_nombre_medicamento).setText(medicamento.nombre_medicamento)
            findViewById<TextInputEditText>(R.id.input_actu_concentracion).setText(medicamento.concentracion.toString())
            findViewById<TextInputEditText>(R.id.input_actu_forma_farmaceutica).setText(medicamento.forma_farmaceutica)
            findViewById<TextInputEditText>(R.id.input_actu_venta_libre).setText(medicamento.venta_libre.toString())
        }
        val boton_actualizar_medicamento = findViewById<Button>(
            R.id.btn_actualizar_medicamento_formulario
        )
        boton_actualizar_medicamento.setOnClickListener{
            val nombre_medicamento = findViewById<TextInputEditText>(R.id.input_actu_nombre_medicamento).text
            val concentracion = findViewById<TextInputEditText>(R.id.input_actu_concentracion).text
            val forma_farmaceutica = findViewById<TextInputEditText>(R.id.input_actu_forma_farmaceutica).text
            val venta_libre = findViewById<TextInputEditText>(R.id.input_actu_venta_libre).text

            val toast = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)
            if ((nombre_medicamento.toString() == "") or(concentracion.toString() == "") or (forma_farmaceutica.toString() == "") or (venta_libre.toString() == "")){
                toast.setText("Ingrese todos los campos")
                toast.show()
            } else {
                val RecetaMedicaBD = SqliteHelperRecetaMedica(this)
                if (medicamento != null) {
                    if (receta_medica_seleccionada != null) {
                        if (RecetaMedicaBD.actualizarMedicamentoFormulario(
                                medicamento.id_medicamento,
                                nombre_medicamento.toString(),
                                concentracion.toString().toDouble(),
                                forma_farmaceutica.toString(),
                                venta_libre.toString().toBoolean(),
                                receta_medica_seleccionada.id_receta
                            )
                        ){
                            findViewById<TextInputEditText>(R.id.input_actu_nombre_medicamento).setText("")
                            findViewById<TextInputEditText>(R.id.input_actu_concentracion).setText("")
                            findViewById<TextInputEditText>(R.id.input_actu_forma_farmaceutica).setText("")
                            findViewById<TextInputEditText>(R.id.input_actu_venta_libre).setText("")
                            toast.setText("El medicamento se actualizo correctamente")
                            toast.show()
                        }
                    }
                }
            }
        }



    }
}