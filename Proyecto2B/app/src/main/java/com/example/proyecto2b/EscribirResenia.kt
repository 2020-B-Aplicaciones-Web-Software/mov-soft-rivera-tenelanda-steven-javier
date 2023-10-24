package com.example.proyecto2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EscribirResenia : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    var calificacion = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_escribir_resenia)

        val clinica = intent.getParcelableExtra<Clinica>("CLINICA")

        val botonCancelarResenia = findViewById<Button>(
            R.id.btn_cancelar
        )
        botonCancelarResenia.setOnClickListener {
            val intent = Intent(
                this,
                DetallesClinica::class.java
            )
            intent.putExtra("CLINICA",clinica)
            startActivityForResult(intent, CODIGO_RESPUESTA_INTENT_EXPLICITO)
        }

        val botonPublicarResenia = findViewById<Button>(
            R.id.btn_publicar
        )
        botonPublicarResenia.setOnClickListener {
            if (clinica != null) {
                crearResenia(clinica)
            }
        }

        val barra_estrellas = findViewById<RatingBar>(R.id.barra_estrellas)
        barra_estrellas.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            calificacion = rating.toDouble()
        }


    }

    fun crearResenia(clinica: Clinica) {

        val nombre_apellido = findViewById<TextInputEditText>(R.id.input_nombre).text
        val comentario = findViewById<EditText>(R.id.input_comentario).text
        val toast = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)
        if ((nombre_apellido.toString() == "") or(comentario.toString() == "")
            or (calificacion.toString() == "") ){
            toast.setText("Ingrese todos los campos")
            toast.show()
        } else{
            val nuevaResenia = hashMapOf<String, Any>(
                "nombre" to nombre_apellido.toString(),
                "calificacion" to calificacion.toInt(),
                "comentario" to comentario.toString()
            )

            val db = Firebase.firestore
            val referenciaResenia = db
                .collection("clinica")
                .whereEqualTo("nombre_clinica", clinica.nombre_clinica)
                .whereEqualTo("latitud", clinica.latitud)
                .whereEqualTo("longitud", clinica.longitud)

            referenciaResenia
                .get()
                .addOnSuccessListener { result ->
                    for (document in result){
                        val referencia_documento = db.collection("clinica").document(document.id)
                        referencia_documento.collection("resenias")
                            .add(nuevaResenia)
                            .addOnSuccessListener {
                                nombre_apellido?.clear()
                                comentario?.clear()
                                toast.setText("Resenia creada exitosamente")
                                toast.show()
                            }


                    }
                }

            if (clinica.resenias != null) {
                if (calificacion == 5.0){
                    clinica.resenias!!.num_5 ++
                }
                if (calificacion == 4.0){
                    clinica.resenias!!.num_4 ++
                }
                if (calificacion == 3.0){
                    clinica.resenias!!.num_3 ++
                }
                if (calificacion == 2.0){
                    clinica.resenias!!.num_2 ++
                }
                if (calificacion == 1.0){
                    clinica.resenias!!.num_1 ++
                }

                clinica.resenias!!.num_resenias++

                var promedio = ((clinica.resenias!!.num_5.toDouble() * 5) +
                        (clinica.resenias!!.num_4.toDouble() * 4) +
                        (clinica.resenias!!.num_3.toDouble() * 3)+
                        (clinica.resenias!!.num_2.toDouble() * 2)+
                        (clinica.resenias!!.num_1.toDouble() * 1))/ clinica.resenias!!.num_resenias.toDouble()

                referenciaResenia
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result){
                            val referencia_documento = db.collection("clinica").document(document.id)
                            referencia_documento.update("resenias.num_5",(clinica.resenias?.num_5))
                            referencia_documento.update("resenias.num_4",(clinica.resenias?.num_4))
                            referencia_documento.update("resenias.num_3",(clinica.resenias?.num_3))
                            referencia_documento.update("resenias.num_2",(clinica.resenias?.num_2))
                            referencia_documento.update("resenias.num_1",(clinica.resenias?.num_1))
                            referencia_documento.update("resenias.num_resenias",(clinica.resenias)?.num_resenias)
                            referencia_documento.update("resenias.promedio", (promedio))
                        }
                    }
            }



            /*if(calificacion== 5.0){

                referenciaResenia
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result){
                            val num_5_actual = "${document["resenias.num_5"]}".toInt()
                            val num_resenias_actual_5 = "${document["resenias.num_resenias"]}".toInt()
                            val referencia_documento = db.collection("clinica").document(document.id)

                            referencia_documento.update("resenias.num_5",(num_5_actual+1))
                            referencia_documento.update("resenias.num_resenias",(num_resenias_actual_5+1))
                            referencia_documento.update("resenias.promedio", (5 * (num_5_actual+1)/ (num_resenias_actual_5+1)))
                        }
                    }
            }

            if(calificacion== 4.0){
                referenciaResenia
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result){
                            val num_4_actual = "${document["resenias.num_4"]}".toInt()
                            val num_resenias_actual_4 = "${document["resenias.num_resenias"]}".toInt()
                            val referencia_documento = db.collection("clinica").document(document.id)
                            referencia_documento.update("resenias.num_4",(num_4_actual+1))
                            referencia_documento.update("resenias.num_resenias",(num_resenias_actual_4+1))
                            referencia_documento.update("resenias.promedio", (4 * (num_4_actual+1)/ (num_resenias_actual_4+1)))
                        }
                    }
            }

            if(calificacion== 3.0){
                referenciaResenia
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result){
                            val num_3_actual = "${document["resenias.num_3"]}".toInt()
                            val num_resenias_actual_3 = "${document["resenias.num_resenias"]}".toInt()
                            val referencia_documento = db.collection("clinica").document(document.id)
                            referencia_documento.update("resenias.num_3",(num_3_actual+1))
                            referencia_documento.update("resenias.num_resenias",(num_resenias_actual_3+1))
                            referencia_documento.update("resenias.promedio", (3 * (num_3_actual+1)/ (num_resenias_actual_3+1)))
                        }
                    }
            }

            if(calificacion== 2.0){
                referenciaResenia
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result){
                            val num_2_actual = "${document["resenias.num_2"]}".toInt()
                            val num_resenias_actual_2 = "${document["resenias.num_resenias"]}".toInt()
                            val referencia_documento = db.collection("clinica").document(document.id)
                            referencia_documento.update("resenias.num_2",(num_2_actual+1))
                            referencia_documento.update("resenias.num_resenias",(num_resenias_actual_2+1))
                            referencia_documento.update("resenias.promedio", (2 * (num_2_actual+1)/ (num_resenias_actual_2+1)))
                        }
                    }
            }

            if(calificacion== 1.0){
                referenciaResenia
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result){
                            val num_1_actual = "${document["resenias.num_1"]}".toInt()
                            val num_resenias_actual_1 = "${document["resenias.num_resenias"]}".toInt()
                            val referencia_documento = db.collection("clinica").document(document.id)
                            referencia_documento.update("resenias.num_1",(num_1_actual+1))
                            referencia_documento.update("resenias.num_resenias",(num_resenias_actual_1+1))
                            referencia_documento.update("resenias.promedio", (1 * (num_1_actual+1)/ (num_resenias_actual_1+1)).toDouble())
                        }
                    }
            }*/

        }





    }
}