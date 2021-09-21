package com.example.examen_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearMedicamento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_medicamento)

        val receta_medica_seleccionada = intent.getParcelableExtra<RecetaMedica>("RECETA_MEDICA_SELECCIONADA1")

        val boton_crear_medicamentos = findViewById<Button>(
            R.id.btn_crear_medicamento_formulario
        )
        boton_crear_medicamentos.setOnClickListener{
            if (receta_medica_seleccionada != null) {
                crearMedicamento(receta_medica_seleccionada)
            }
        }
    }

    fun crearMedicamento(receta_medica: RecetaMedica) {
        val nombre_medicamento = findViewById<TextInputEditText>(R.id.input_nombre_medicamento).text
        val concentracion = findViewById<TextInputEditText>(R.id.input_concentracion).text
        val forma_farmaceutica = findViewById<TextInputEditText>(R.id.input_forma_farmaceutica).text
        val venta_libre = findViewById<TextInputEditText>(R.id.input_venta_libre).text


        val toast = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)
        if ((nombre_medicamento.toString() == "") or(concentracion.toString() == "") or (forma_farmaceutica.toString() == "") or (venta_libre.toString() == "")){
            toast.setText("Ingrese todos los campos")
            toast.show()
        } else {
            val nuevoMedicamento = hashMapOf<String, Any>(
                "nombre_medicamento" to nombre_medicamento.toString(),
                "concentracion" to concentracion.toString().toDouble(),
                "forma_farmaceutica" to forma_farmaceutica.toString(),
                "venta_libre" to venta_libre.toString().toBoolean()
            )

            val db = Firebase.firestore
            val referenciaMedicamento = db
                .collection("receta_medica")
                .whereEqualTo("nombre_paciente", receta_medica.nombre_paciente)
                .whereEqualTo("edad", receta_medica.edad)
                .whereEqualTo("diagnostico", receta_medica.diagnostico)
                .whereEqualTo("frecuencia_duracion_tratamiento", receta_medica.frecuencia_duracion_tratamiento)

            referenciaMedicamento
                .get()
                .addOnSuccessListener { result ->
                    for (document in result){
                        val referencia_documento = db.collection("receta_medica").document(document.id)
                        referencia_documento.collection("medicamento")
                            .add(nuevoMedicamento)
                            .addOnSuccessListener {
                                nombre_medicamento?.clear()
                                concentracion?.clear()
                                forma_farmaceutica?.clear()
                                venta_libre?.clear()
                                toast.setText("Medicamento creado exitosamente")
                                toast.show()
                            }
                    }
                }
        }
    }
}