package com.example.receta_medica_medicamento

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class CrearMedicamento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_medicamento)

        val receta_medica_seleccionada = intent.getParcelableExtra<RecetaMedica>("RECETA_MEDICA_SELECCIONADA1")

        val boton_crear_medicamentos = findViewById<Button>(
            R.id.btn_crear_medicamento_formulario
        )
        boton_crear_medicamentos.setOnClickListener{
            val nombre_medicamento = findViewById<TextInputEditText>(R.id.input_nombre_medicamento).text
            val concentracion = findViewById<TextInputEditText>(R.id.input_concentracion).text
            val forma_farmaceutica = findViewById<TextInputEditText>(R.id.input_forma_farmaceutica).text
            val venta_libre = findViewById<TextInputEditText>(R.id.input_venta_libre).text
            val id_receta_medica = receta_medica_seleccionada?.id_receta

            val toast = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)
            if ((nombre_medicamento.toString() == "") or(concentracion.toString() == "") or (forma_farmaceutica.toString() == "") or (venta_libre.toString() == "")){
                toast.setText("Ingrese todos los campos")
                toast.show()
            } else {
                val medicamento = SqliteHelperRecetaMedica(this)
                if (medicamento.crearMedicamentoFormulario(nombre_medicamento.toString(), concentracion.toString().toDouble(), forma_farmaceutica.toString(), venta_libre.toString().toBooleanStrict(),id_receta_medica.toString().toInt()) ) {
                    findViewById<TextInputEditText>(R.id.input_nombre_medicamento).setText("")
                    findViewById<TextInputEditText>(R.id.input_concentracion).setText("")
                    findViewById<TextInputEditText>(R.id.input_forma_farmaceutica).setText("")
                    findViewById<TextInputEditText>(R.id.input_venta_libre).setText("")
                    toast.setText("Medicamento creado exitosamente")
                    toast.show()
                }
            }
        }
    }
}