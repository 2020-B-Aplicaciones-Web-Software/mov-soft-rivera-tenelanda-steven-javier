package com.example.moviles_software_2021_a

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

class VistaRecetaMedica : AppCompatActivity() {
    var RecetaMedicaBD = SqliteHelperRecetaMedica(this)
    var arregloRecetasMedicas1 = ArrayList<RecetaMedica>()
    var id_seleccionado = -1
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_receta_medica)

        mostrarRecetaMedicaListView()
        val btn_anadir_receta_medica = findViewById<Button>(
            R.id.btn_anadir_receta_medica
        )
        btn_anadir_receta_medica.setOnClickListener {
            val intentExplicito = Intent (
                this,
                FormularioRecetaMedica::class.java
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
        val listViewRecetasMedicas = findViewById<ListView>(R.id.txv_receta_medica)
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
                RecetaMedicaBD.eliminarRecetaMedicaFormulario(arregloRecetasMedicas1[id_seleccionado].receta_id)
                onResume()
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

}