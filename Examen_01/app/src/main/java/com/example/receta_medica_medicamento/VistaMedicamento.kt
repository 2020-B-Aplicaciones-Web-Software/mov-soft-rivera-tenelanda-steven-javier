package com.example.receta_medica_medicamento

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

class VistaMedicamento : AppCompatActivity() {
    var RecetaMedicaBD = SqliteHelperRecetaMedica(this)
    var arregloMedicamentos1 = ArrayList<Medicamento>()
    var id_seleccionado = -1
    var CODIGO_RESPUESTA_INTENT_EXPLICITO = 403
    var receta_medica = RecetaMedica(0,"",0,"","",null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_medicamento)
        mostrarMedicamentosListView()
        val receta_medica_seleccionada = intent.getParcelableExtra<RecetaMedica>("RECETASELECCIONADA")
        if (receta_medica_seleccionada != null) {
            receta_medica = receta_medica_seleccionada
        }
        if(receta_medica_seleccionada != null){
            mostrarMedicamentosListView()
        }

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
        mostrarMedicamentosListView()
    }

    fun mostrarMedicamentosListView() {
        findViewById<TextView>(R.id.txv_mostrar_nombre).setText(receta_medica.nombre_paciente)
        val arregloMedicamentos = RecetaMedicaBD.consultarMedicamentos(receta_medica.id_receta)
        arregloMedicamentos1 = arregloMedicamentos

        //Creacion del adaptador
        val adaptador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1, //Se define el Layout
            arregloMedicamentos
        )
        //Se asigna el adaptador de la lista
        val listViewMedicamentos = findViewById<ListView>(R.id.lv_medicamentos)
        registerForContextMenu(listViewMedicamentos)
        listViewMedicamentos.adapter = adaptador
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
                        RecetaMedicaBD.eliminarMedicamentoFormulario(arregloMedicamentos1[id_seleccionado].id_medicamento)
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
            else -> super.onContextItemSelected(item)
        }
    }

    fun irFormularioEditar() {
        val editarMedicamentoForm = Intent(
            this,
            ActualizarMedicamento::class.java
        )
        editarMedicamentoForm.putExtra("MEDICAMENTO", arregloMedicamentos1[id_seleccionado])
        editarMedicamentoForm.putExtra("RECETA_MEDICA_SELECCIONADA", receta_medica)
        startActivityForResult(editarMedicamentoForm, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }


}