package com.example.examen_02

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MostrarMedicamento : AppCompatActivity() {

    var arregloMedicamentos1 = ArrayList<Medicamento>()
    var id_seleccionado = -1
    var CODIGO_RESPUESTA_INTENT_EXPLICITO = 403
    var receta_medica = RecetaMedica("",0,"","",null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_medicamento)

        val receta_medica_seleccionada = intent.getParcelableExtra<RecetaMedica>("RECETASELECCIONADA")
        if (receta_medica_seleccionada != null) {
            receta_medica = receta_medica_seleccionada
        }
        mostrarMedicamentos()

        val boton_crear_medicamento = findViewById<Button>(
            R.id.btn_crear_medicamento
        )
        boton_crear_medicamento.setOnClickListener{
            val intentExplicito = Intent (
                this,
                CrearMedicamento::class.java
            )
            intentExplicito.putExtra("RECETA_MEDICA_SELECCIONADA1", receta_medica_seleccionada)
            startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
        }
    }

    override fun onResume() {
        super.onResume()
        mostrarMedicamentos()
    }

    fun mostrarMedicamentos() {
        findViewById<TextView>(R.id.txv_mostrar_nombre).setText(receta_medica.nombre_paciente)

        val db = Firebase.firestore
        val referenciaMedicamentos = db
            .collection("receta_medica")
            .whereEqualTo("nombre_paciente", receta_medica.nombre_paciente)
            .whereEqualTo("edad", receta_medica.edad)
            .whereEqualTo("diagnostico", receta_medica.diagnostico)
            .whereEqualTo("frecuencia_duracion_tratamiento", receta_medica.frecuencia_duracion_tratamiento)

        val arreglo_medicamentos = ArrayList<Medicamento>()
        referenciaMedicamentos
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                    val referencia_documento = db.collection("receta_medica").document(document.id)
                    referencia_documento.collection("medicamento")
                        .get()
                        .addOnSuccessListener { resultado ->
                            for (documento in resultado){
                                val nombre_medicamento = "${documento.data.get("nombre_medicamento")}"
                                val concentracion = "${documento.data.get("concentracion")}"
                                val forma_farmaceutica = "${documento.data.get("forma_farmaceutica")}"
                                val venta_libre = "${documento.data.get("venta_libre")}"
                                val MedicamentoCargado = Medicamento(nombre_medicamento, concentracion.toDouble(),forma_farmaceutica, venta_libre.toBoolean())
                                arreglo_medicamentos.add(MedicamentoCargado)
                            }

                            arregloMedicamentos1 = arreglo_medicamentos
                            //Creacion del adaptador
                            val adaptador = ArrayAdapter(
                                this, //contexto
                                android.R.layout.simple_list_item_1, //Se define el Layout
                                arregloMedicamentos1
                            )
                            //Se asigna el adaptador de la lista
                            val listViewMedicamentos = findViewById<ListView>(R.id.lv_medicamentos)
                            registerForContextMenu(listViewMedicamentos)
                            listViewMedicamentos.adapter = adaptador

                        }

                }
            }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu1, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        id_seleccionado = info.position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi1_editar -> {
                mostrarMedicamentos()
                irFormularioEditar()
                return true
            }
            R.id.mi1_eliminar -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Medicamento")
                builder.setMessage("Desea elminar el medicamento seleccionado")

                builder.setPositiveButton(
                    "Si", DialogInterface.OnClickListener { dialog, which ->
                        Log.i("list-view", "Si")

                        val db = Firebase.firestore
                        val referenciaMedicamentos = db
                            .collection("receta_medica")
                            .whereEqualTo("nombre_paciente", receta_medica.nombre_paciente)
                            .whereEqualTo("edad", receta_medica.edad)
                            .whereEqualTo("diagnostico", receta_medica.diagnostico)
                            .whereEqualTo("frecuencia_duracion_tratamiento", receta_medica.frecuencia_duracion_tratamiento)

                        referenciaMedicamentos
                            .get()
                            .addOnSuccessListener { result ->
                                for (document in result){
                                    val referencia_documento = db.collection("receta_medica").document(document.id)
                                    referencia_documento.collection("medicamento")

                                        .whereEqualTo("nombre_medicamento", arregloMedicamentos1[id_seleccionado].nombre_medicamento)
                                        .whereEqualTo("concentracion", arregloMedicamentos1[id_seleccionado].concentracion)
                                        .whereEqualTo("forma_farmaceutica", arregloMedicamentos1[id_seleccionado].forma_farmaceutica)
                                        .whereEqualTo("venta_libre", arregloMedicamentos1[id_seleccionado].venta_libre)
                                        .get()
                                        .addOnSuccessListener { resultado ->
                                            for (documento in resultado){
                                                referencia_documento.collection("medicamento").document(documento.id).delete()
                                            }
                                        }
                                    onResume()
                                }
                            }


                    }
                )
                builder.setNegativeButton(
                    "No",
                    null
                )
                val dialogo = builder.create()
                dialogo.show()

                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun irFormularioEditar() {
        mostrarMedicamentos()
        val editarMedicamentoForm = Intent(
            this,
            ActualizarMedicamento::class.java
        )
        editarMedicamentoForm.putExtra("MEDICAMENTO", arregloMedicamentos1[id_seleccionado])
        editarMedicamentoForm.putExtra("RECETA_MEDICA_SELECCIONADA", receta_medica)
        startActivityForResult(editarMedicamentoForm, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }



}