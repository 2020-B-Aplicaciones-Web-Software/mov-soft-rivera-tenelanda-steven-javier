package com.example.examen_02

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
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MostrarRecetaMedica : AppCompatActivity() {

    var arregloRecetasMedicas1 = ArrayList<RecetaMedica>()
    var id_seleccionado = -1
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_receta_medica)

        val boton_crear_receta_medica = findViewById<Button>(
            R.id.btn_crear_receta
        )
        boton_crear_receta_medica.setOnClickListener {
            val intentExplicito = Intent (
                this,
                CrearRecetaMedica::class.java
            )
            startActivity(intentExplicito)
        }
    }

    override fun onResume() {
        super.onResume()
        mostrarRecetaMedica()
    }

    fun mostrarRecetaMedica() {
        val db = Firebase.firestore
        val referenciaRecetaMedica = db
            .collection("receta_medica")
        val arregloRecetasMedicas = ArrayList<RecetaMedica>()

        referenciaRecetaMedica
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                    val nombre_paciente = "${document.data.get("nombre_paciente")}"
                    val edad = "${document.data.get("edad")}"
                    val diagnostico = "${document.data.get("diagnostico")}"
                    val frecuencia = "${document.data.get("frecuencia_duracion_tratamiento")}"
                    val RecetaMedicaCargada = RecetaMedica(nombre_paciente,edad.toInt(),diagnostico,frecuencia,null)
                    arregloRecetasMedicas.add(RecetaMedicaCargada)
                }

                arregloRecetasMedicas1 = arregloRecetasMedicas
                //Creacion del adaptador
                val adaptador = ArrayAdapter(
                    this, //contexto
                    android.R.layout.simple_list_item_1, //Se define el Layout
                    arregloRecetasMedicas1
                )
                //Se asigna el adaptador de la lista
                val listViewRecetasMedicas = findViewById<ListView>(R.id.lv_recetas_medicas)
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
            R.id.mi_editar -> {
                mostrarRecetaMedica()
                irFormularioEditar()
                return true
            }
            R.id.mi_eliminar -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Receta Medica")
                builder.setMessage("Desea elminar la receta medica seleccionada")

                builder.setPositiveButton(
                    "Si", DialogInterface.OnClickListener { dialog, which ->
                        Log.i("list-view", "Si")

                        val db = Firebase.firestore
                        val referenciaRecetaMedica = db
                            .collection("receta_medica")
                            .whereEqualTo("nombre_paciente", arregloRecetasMedicas1[id_seleccionado].nombre_paciente)
                            .whereEqualTo("edad", arregloRecetasMedicas1[id_seleccionado].edad)
                            .whereEqualTo("diagnostico", arregloRecetasMedicas1[id_seleccionado].diagnostico)
                            .whereEqualTo("frecuencia_duracion_tratamiento", arregloRecetasMedicas1[id_seleccionado].frecuencia_duracion_tratamiento)

                        referenciaRecetaMedica
                            .get()
                            .addOnSuccessListener { result ->
                                for (document in result){
                                    val referencia_documento = db.collection("receta_medica").document(document.id)
                                    referencia_documento.delete()
                                }
                                onResume()
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
            R.id.mi_medicamentos -> {
                mostrarRecetaMedica()
                irVistaMedicamento()
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    fun irFormularioEditar() {
        mostrarRecetaMedica()
        val editarRecetaMedicaForm = Intent(
            this,
            ActualizarRecetaMedica::class.java
        )
        editarRecetaMedicaForm.putExtra("RECETAMEDICA", arregloRecetasMedicas1[id_seleccionado])
        startActivityForResult(editarRecetaMedicaForm, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    fun irVistaMedicamento() {
        mostrarRecetaMedica()
        val vistaMedicamentoForm = Intent(
            this,
            MostrarMedicamento::class.java
        )

        vistaMedicamentoForm.putExtra("RECETASELECCIONADA", arregloRecetasMedicas1[id_seleccionado])
        startActivityForResult(vistaMedicamentoForm, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }
}