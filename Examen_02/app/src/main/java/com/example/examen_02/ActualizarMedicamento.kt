package com.example.examen_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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
            findViewById<TextInputEditText>(R.id.input_actu_latitud).setText(medicamento.latitud.toString())
            findViewById<TextInputEditText>(R.id.input_actu_longitud).setText(medicamento.longitud.toString())
        }
        val boton_actualizar_medicamento = findViewById<Button>(
            R.id.btn_actualizar_medicamento_formulario
        )

        boton_actualizar_medicamento.setOnClickListener{
            val nombre_medicamento = findViewById<TextInputEditText>(R.id.input_actu_nombre_medicamento).text
            val concentracion = findViewById<TextInputEditText>(R.id.input_actu_concentracion).text
            val forma_farmaceutica = findViewById<TextInputEditText>(R.id.input_actu_forma_farmaceutica).text
            val venta_libre = findViewById<TextInputEditText>(R.id.input_actu_venta_libre).text
            val latitud = findViewById<TextInputEditText>(R.id.input_actu_latitud).text
            val longitud = findViewById<TextInputEditText>(R.id.input_actu_longitud).text

            val toast = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)
            if ((nombre_medicamento.toString() == "") or(concentracion.toString() == "")
                or (forma_farmaceutica.toString() == "") or (venta_libre.toString() == "") or (latitud.toString()== "") or (longitud.toString() == "")){
                toast.setText("Ingrese todos los campos")
                toast.show()
            } else {

                val db = Firebase.firestore
                val referenciaMedicamentos = db
                    .collection("receta_medica")
                    .whereEqualTo("nombre_paciente", receta_medica_seleccionada?.nombre_paciente)
                    .whereEqualTo("edad", receta_medica_seleccionada?.edad)
                    .whereEqualTo("diagnostico", receta_medica_seleccionada?.diagnostico)
                    .whereEqualTo("frecuencia_duracion_tratamiento", receta_medica_seleccionada?.frecuencia_duracion_tratamiento)

                referenciaMedicamentos
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result){
                            val referencia_documento = db.collection("receta_medica").document(document.id)
                            referencia_documento.collection("medicamento")

                                .whereEqualTo("nombre_medicamento", medicamento?.nombre_medicamento)
                                .whereEqualTo("concentracion", medicamento?.concentracion)
                                .whereEqualTo("forma_farmaceutica", medicamento?.forma_farmaceutica)
                                .whereEqualTo("venta_libre", medicamento?.venta_libre)
                                .whereEqualTo("latitud", medicamento?.latitud)
                                .whereEqualTo("longitud", medicamento?.longitud)
                                .get()
                                .addOnSuccessListener { resultado ->
                                    for (documento in resultado){
                                        val medicamento_referencia = referencia_documento.collection("medicamento").document(documento.id)
                                        medicamento_referencia.update("nombre_medicamento", nombre_medicamento.toString())
                                        medicamento_referencia.update("concentracion", concentracion.toString().toDouble())
                                        medicamento_referencia.update("forma_farmaceutica", forma_farmaceutica.toString())
                                        medicamento_referencia.update("venta_libre", venta_libre.toString().toBoolean())
                                        medicamento_referencia.update("latitud", latitud.toString().toDouble())
                                        medicamento_referencia.update("longitud", longitud.toString().toDouble())
                                    }
                                    findViewById<TextInputEditText>(R.id.input_actu_nombre_medicamento).setText("")
                                    findViewById<TextInputEditText>(R.id.input_actu_concentracion).setText("")
                                    findViewById<TextInputEditText>(R.id.input_actu_forma_farmaceutica).setText("")
                                    findViewById<TextInputEditText>(R.id.input_actu_venta_libre).setText("")
                                    findViewById<TextInputEditText>(R.id.input_actu_latitud).setText("")
                                    findViewById<TextInputEditText>(R.id.input_actu_longitud).setText("")
                                    toast.setText("El medicamento se actualizo correctamente")
                                    toast.show()
                                }

                        }
                    }

            }
        }
    }
}