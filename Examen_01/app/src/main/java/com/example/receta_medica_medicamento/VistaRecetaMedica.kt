package com.example.receta_medica_medicamento

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
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class VistaRecetaMedica : AppCompatActivity() {
    var RecetaMedicaBD = SqliteHelperRecetaMedica(this)
    var arregloRecetasMedicas1 = ArrayList<RecetaMedica>()
    var id_seleccionado = -1
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_receta_medica)

        mostrarRecetaMedicaListView()
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
        mostrarRecetaMedicaListView()
    }

    fun mostrarRecetaMedicaListView() {
        val arregloRecetasMedicas = RecetaMedicaBD.consultarRecetasMedicas()
        arregloRecetasMedicas1 = arregloRecetasMedicas

        //Creacion del adaptador
        val adaptador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1, //Se define el Layout
            arregloRecetasMedicas
        )
        //Se asigna el adaptador de la lista
        val listViewRecetasMedicas = findViewById<ListView>(R.id.lv_recetas_medicas)
        registerForContextMenu(listViewRecetasMedicas)
        listViewRecetasMedicas.adapter = adaptador

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
                        RecetaMedicaBD.eliminarRecetaMedicaFormulario(arregloRecetasMedicas1[id_seleccionado].id_receta)
                        onResume()
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
                irVistaMedicamento()
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    fun irFormularioEditar() {
        val editarRecetaMedicaForm = Intent(
            this,
            ActualizarRecetaMedica::class.java
        )
        editarRecetaMedicaForm.putExtra("RECETAMEDICA", arregloRecetasMedicas1[id_seleccionado])
        startActivityForResult(editarRecetaMedicaForm, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    fun irVistaMedicamento() {
        val vistaMedicamentoForm = Intent(
            this,
            VistaMedicamento::class.java
        )

        vistaMedicamentoForm.putExtra("RECETASELECCIONADA", arregloRecetasMedicas1[id_seleccionado])
        startActivityForResult(vistaMedicamentoForm, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }
}