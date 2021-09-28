package com.example.proyecto2b

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MostrarClinica : AppCompatActivity() {
    var arregloClinicas1 = ArrayList<Clinica>()
    var id_seleccionado = -1
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_clinica)


    }

    override fun onResume() {
        super.onResume()
        mostrarClinicas()
    }

    fun mostrarClinicas() {
        val db = Firebase.firestore
        val referenciaClinica = db.collection("clinica")
        val arregloClinicas = ArrayList<Clinica>()
        val arregloServicios = ArrayList<Servicio>()
        referenciaClinica
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                    val nombre_clinica = "${document.data.get("nombre_clinica")}"
                    val foto_logo = "${document.data.get("foto_logo")}"
                    val direccion_clinica = "${document.data.get("direccion_clinica")}"
                    val telefono_clinica = "${document.data.get("telefono_clinica")}"
                    val web_clinica = "${document.data.get("web_clinica")}"
                    val costo_consulta = "${document.data.get("costo_consulta")}"
                    val novedades = "${document.data.get("novedades")}"
                    val latitud = "${document.data.get("latitud")}"
                    val longitud = "${document.data.get("longitud")}"
                    //RESENIAS MAP
                    val num_5 = "${document["resenias.num_5"]}"
                    val num_4 = "${document["resenias.num_5"]}"
                    val num_3 = "${document["resenias.num_5"]}"
                    val num_2 = "${document["resenias.num_5"]}"
                    val num_1 = "${document["resenias.num_5"]}"
                    val num_resenias = "${document["resenias.num_resenias"]}"
                    val promedio = "${document["resenias.promedio"]}"
                    //HORARIOS ATENCION MAP
                    val lunes = "${document["horarios_atencion.lunes"]}"
                    val martes = "${document["horarios_atencion.martes"]}"
                    val miercoles = "${document["horarios_atencion.miercoles"]}"
                    val jueves = "${document["horarios_atencion.jueves"]}"
                    val viernes = "${document["horarios_atencion.viernes"]}"
                    val sabado = "${document["horarios_atencion.sabado"]}"
                    val domingo = "${document["horarios_atencion.domingo"]}"


                    val referenciaServicio = referenciaClinica.document(document.id).collection("servicios")
                    referenciaServicio.get()
                        .addOnSuccessListener { result1->
                            for (document1 in result1){
                                val nombre_servicio = "${document1.data.get("nombre_servicio")}"
                                val costo_servicio = "${document1.data.get("costo_servicio")}"
                                arregloServicios.add(Servicio(nombre_servicio,costo_servicio.toDouble()))
                            }
                        }


                    val ClinicaCargada = Clinica(
                        nombre_clinica,
                        foto_logo,
                        direccion_clinica,
                        telefono_clinica,
                        web_clinica,
                        costo_consulta.toDouble(),
                        novedades,
                        latitud.toDouble(),
                        longitud.toDouble(),
                        ReseniaEvaluacion(num_5.toInt(),num_4.toInt(),num_3.toInt(),num_2.toInt(),num_1.toInt(),promedio.toInt(),num_resenias.toInt()),
                        HorariosAtencion(lunes,martes,miercoles,jueves,viernes,sabado,domingo),
                        arregloServicios
                    )
                    arregloClinicas.add(ClinicaCargada)
                }

                arregloClinicas1 = arregloClinicas
                //Creacion del adaptador
                val adaptador = ArrayAdapter(
                    this, //contexto
                    android.R.layout.simple_list_item_1, //Se define el Layout
                    arregloClinicas1
                )
                //Se asigna el adaptador de la lista
                val listViewRecetasMedicas = findViewById<ListView>(R.id.rv_clinicas)
                registerForContextMenu(listViewRecetasMedicas)
                listViewRecetasMedicas.adapter = adaptador
            }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        id_seleccionado = info.position

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.menu_ir_clinica -> {
                mostrarClinicas()
                irDetallesClinica()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun irDetallesClinica() {
        mostrarClinicas()
        val irdetalleClinica = Intent(
            this,
            DetallesClinica::class.java
        )
        irdetalleClinica.putExtra("CLINICA", arregloClinicas1[id_seleccionado])
        startActivityForResult(irdetalleClinica, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

}