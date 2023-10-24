package com.example.proyecto2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonCargarClinicas = findViewById<Button>(R.id.btn_cargar_clinicas)
        botonCargarClinicas.setOnClickListener {

            val nombre_clinica = "La casa del Perro"
            val foto_logo = "url_imagen"
            val direccion_clinica = "Alonso de Angulo y Amazonas S15- 46"
            val telefono_clinica = "239742093"
            val web_clinica = "url de la pagina web de la clinica"
            val costo_consulta = 20.50
            val novedades = "Corte de pelo 2x1 los dias lunes"
            val latitud = -45.234234234
            val longitud = -45.293849234
            val resenias = ReseniaEvaluacion(0,0,0,0,0,0.0,0)
            val horarios_atencion = HorariosAtencion("10:00-17:00","10:00-17:00","10:00-17:00","10:00-17:00","10:00-17:00","Cerrado","Cerrado")
            val servicios = arrayListOf<Servicio>(
                Servicio("Vacunas", 15.00),
                Servicio("Esterilizacion", 50.00),
                Servicio("Chequeos periodicos", 20.00)
            )




            val horarios = hashMapOf<String, Any?>(
                "lunes" to horarios_atencion.lunes,
                "martes" to horarios_atencion.martes,
                "miercoles" to horarios_atencion.miercoles,
                "jueves" to horarios_atencion.jueves,
                "viernes" to horarios_atencion.viernes,
                "sabado" to horarios_atencion.sabado,
                "domingo" to horarios_atencion.domingo
            )

            val reseniasEvaluacion = hashMapOf<String, Any>(
                "num_5" to resenias.num_5,
                "num_4" to resenias.num_4,
                "num_3" to resenias.num_3,
                "num_2" to resenias.num_2,
                "num_1" to resenias.num_1,
                "promedio" to resenias.promedio,
                "num_resenias" to resenias.num_resenias
            )

            val nuevaClinica = hashMapOf<String, Any>(
                "nombre_clinica" to nombre_clinica,
                "foto_logo" to foto_logo,
                "direccion_clinica" to direccion_clinica,
                "telefono_clinica" to telefono_clinica,
                "web_clinica" to web_clinica,
                "costo_consulta" to costo_consulta,
                "novedades" to novedades,
                "latitud" to latitud,
                "longitud" to longitud,
                "resenias" to reseniasEvaluacion,
                "horarios_atencion" to horarios
            )

            val db = Firebase.firestore
            val referencia = db.collection("clinica")

            referencia
                .add(nuevaClinica)
                .addOnSuccessListener {
                    val toast = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)
                    toast.setText("Clinicas Cargadas")
                    toast.show()
                }

            referencia
                .whereEqualTo("nombre_clinica", nombre_clinica)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val referencia_documento = db.collection("clinica").document(document.id)
                        for (servicio in servicios){
                            referencia_documento.collection("servicios")
                                .add(servicio)
                                .addOnSuccessListener {  }
                        }

                    }
                }



        }

        val botonVerRenias = findViewById<Button>(R.id.btn_ver_resenias)
        botonVerRenias.setOnClickListener {
            val intent = Intent(
                this,
                MostrarResenias::class.java
            )
            startActivity(intent)
        }

        val botonMostrarClinicas = findViewById<Button>(R.id.btn_mostrar_clinicas)
        botonMostrarClinicas.setOnClickListener {
            val intent = Intent(
                this,
                MostrarClinica::class.java
            )
            startActivity(intent)
        }


    }
}