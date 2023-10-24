package com.example.moviles_software_2021_a

import android.content.DialogInterface
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
import java.util.ArrayList

class BListView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        //Inicializamos el arregloNumeros con la base de datos que esta en memoria.
        val arregloNumero = BBaseDatosMemoria.arregloBEntrenador

        //Cracion del arreglo de numeros
       // val arregloNumero = arrayListOf<BEntrenador>(
         //   BEntrenador("Adrian", "a@a.com"),
        // BEntrenador("Vicente", "b@b.com"),
        //  BEntrenador("Carolina", "c@c.com")
        //)

        //Creacion del adaptador
        val adaptador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1, //Se define el Layout
            arregloNumero
        )

        //Se asigna el adaptador a la lista
        val listViewEjemplo = findViewById<ListView>(R.id.txv_ejemplo)
        listViewEjemplo.adapter = adaptador

        //Se define la accion del boton
        val botonListView = findViewById<Button>(R.id.btn_list_view_anadir)
        botonListView.setOnClickListener{
            anadirItemsAlListView(BEntrenador("Carolina", "c@c.com", null), arregloNumero, adaptador)
        }

        listViewEjemplo
            .setOnItemLongClickListener { adapterView, view, posicion, id ->
                Log.i("list-view", "Dio click ${posicion}")

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Titulo")
                //builder.setMessage("Mensaje")

                val seleccionUsuario = booleanArrayOf(
                    true,
                    false,
                    true
                )
                val opciones = resources.getStringArray(R.array.string_array_opciones_dialogo)

                builder.setMultiChoiceItems(
                    opciones,
                    seleccionUsuario,
                    { dialog, which, isChecked ->
                        Log.i("list-view", "${which} ${isChecked}")
                    }
                )

                builder.setPositiveButton(
                    "Si", DialogInterface.OnClickListener{ dialog, which ->
                    Log.i("list-view", "Si")
                }
                )

                builder.setNegativeButton(
                    "No",
                    null
                )

                val dialog = builder.create()

                dialog.show()
                return@setOnItemLongClickListener true
        }

       // registerForContextMenu(listViewEjemplo)
    }

    var posicionItemSeleccionado = 0
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        //obtener la posicion donde se hizo clic
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionItemSeleccionado = id
        Log.i("list-view", "List view ${posicionItemSeleccionado}")
        Log.i("list-view", "Entrenador ${BBaseDatosMemoria.arregloBEntrenador[id]}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId){
            //Boton Editar
            R.id.mi_editar ->{
                Log.i(
                    "list-view", "Editar ${
                        BBaseDatosMemoria.arregloBEntrenador[
                                posicionItemSeleccionado
                        ]
                    }"
                )
                return true
            }
            //Boton Eliminar
            R.id.mi_eliminar ->{
                Log.i(
                    "list-view", "Eliminar ${
                        BBaseDatosMemoria.arregloBEntrenador[
                                posicionItemSeleccionado
                        ]
                    }"
                )
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }


    fun anadirItemsAlListView(
        valor : BEntrenador,
        arreglo: ArrayList<BEntrenador>,
        adaptador: ArrayAdapter<BEntrenador>
    ){
        arreglo.add(valor)
        adaptador.notifyDataSetChanged()
    }


}