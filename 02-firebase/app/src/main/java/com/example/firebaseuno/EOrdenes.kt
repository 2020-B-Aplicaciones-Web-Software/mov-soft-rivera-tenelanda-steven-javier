package com.example.firebaseuno

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class EOrdenes : AppCompatActivity() {
    var arregloOrdenTotal = arrayListOf<BOrdenesFirebase>()
    var id_seleccionado = -1
    var posicion_seleccionada = -1
    var listaproductos = arrayListOf<BOrdenesFirebase>()
    var labelNombreproductos1 = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eordenes)

        setearProductosFirebase()
        setearRestaurantesFirebase()
        val botonAdd = findViewById<Button>(R.id.btn_anadir_lista_producto)
        botonAdd.setOnClickListener {
            val editTextCantidadProducto = findViewById<EditText>(R.id.et_cantidad_producto)
            for (producto in listaproductos){
                if(labelNombreproductos1[posicion_seleccionada] == producto.nombre ){
                    producto.cantidad = editTextCantidadProducto.text.toString().toInt()
                    arregloOrdenTotal.add(producto)
                }
            }
            mostrarProductosListView()
            calculoTotal()
            editTextCantidadProducto.setText("")

        }




    }

    fun setearProductosFirebase(){
        val db = Firebase.firestore
        val referenciaProductos = db
            .collection("producto")
        val listaProductos = arrayListOf<BOrdenesFirebase>()
        val labelNombreProductos = arrayListOf<String>()
        var arregloOrden = arrayListOf<BOrdenesFirebase>()

        referenciaProductos
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                    val nombre = "${document.data.get("nombre")}"
                    val precio = "${document.data.get("precio")}"
                    val productoCargado = BOrdenesFirebase(nombre,precio.toDouble(),0)
                    listaProductos.add(productoCargado)
                    labelNombreProductos.add(productoCargado.nombre)
                }
                listaproductos = listaProductos
                labelNombreproductos1 = labelNombreProductos

                val spinnerProductos = findViewById<Spinner>(R.id.sp_producto)
                val adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item,
                    labelNombreProductos
                )
                spinnerProductos.adapter = adaptador

                spinnerProductos.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        posicion_seleccionada = position



                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }
            }
    }

    fun setearRestaurantesFirebase(){
        val db = Firebase.firestore
        val referenciaRestaurante = db
            .collection("restaurante")
        val labelNombreRestaurantes = arrayListOf<String>()
        referenciaRestaurante
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                    val nombre = "${document.data.get("nombre")}"
                    labelNombreRestaurantes.add(nombre)
                }

                val spinnerRestaurantes = findViewById<Spinner>(R.id.sp_restaurantes)
                val adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item,
                    labelNombreRestaurantes
                )
                spinnerRestaurantes.adapter = adaptador
            }
    }

    fun mostrarProductosListView() {

        //Creacion del adaptador
        val adaptador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1, //Se define el Layout
            arregloOrdenTotal
        )
        //Se asigna el adaptador de la lista
        val listViewProductos = findViewById<ListView>(R.id.lv_lista_productos)
        registerForContextMenu(listViewProductos)
        listViewProductos.adapter = adaptador

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_producto, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        id_seleccionado = info.position

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.menu_eliminar -> {
                arregloOrdenTotal.removeAt(id_seleccionado)
                mostrarProductosListView()
                calculoTotal()
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    fun calculoTotal(){
        val TextViewTotal = findViewById<TextView>(R.id.tv_total)
        var sumatotal = 0.0
        for (producto in arregloOrdenTotal){
            val total = producto.precio * producto.cantidad
            sumatotal = sumatotal + total
        }
        TextViewTotal.setText("Total: ${sumatotal.toString()}")


    }
}


