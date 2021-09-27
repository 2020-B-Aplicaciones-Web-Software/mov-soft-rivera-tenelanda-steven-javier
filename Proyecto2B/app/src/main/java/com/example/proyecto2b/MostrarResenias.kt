package com.example.proyecto2b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MostrarResenias : AppCompatActivity() {

    var arregloClinicas1 = ArrayList<Clinica>()
    var id_seleccionado = -1
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_resenias)
    }

    override fun onResume() {
        super.onResume()
        mostrarClinicas()
    }

    fun mostrarClinicas() {
        val db = Firebase.firestore
        val referenciaClinicas = db
            .collection("clinica")
        val arregloClinicas = ArrayList<Clinica>()

        referenciaClinicas
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val nombre_clinica = "${document.data.get("nombre_clinica")}"
                    val foto_logo = "${document.data.get("foto_logo")}"
                    val direccion_clinica = "${document.data.get("direccion_clinica")}"
                    val telefono_clinica = "${document.data.get("telefono_clinica")}"
                    val web_clinica = "${document.data.get("web_clinica")}"
                    val costo_consulta = "${document.data.get("costo_consulta")}"
                    val novedades = "${document.data.get("novedades")}"
                    val latitud = "${document.data.get("latitud")}"
                    val longitud = "${document.data.get("longitud")}"


                    val resenia = ReseniaEvaluacion(
                        "${document["resenias.num_5"]}".toInt(),
                        "${document["resenias.num_4"]}".toInt(),
                        "${document["resenias.num_3"]}".toInt(),
                        "${document["resenias.num_2"]}".toInt(),
                        "${document["resenias.num_1"]}".toInt(),
                        "${document["resenias.promedio"]}".toInt(),
                        "${document["resenias.num_resenias"]}".toInt()
                    )

                    
                }

            }
    }
}