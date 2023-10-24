package com.example.examen_02

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Mapa : AppCompatActivity() {

    private lateinit var mapa: GoogleMap

    var permisos = false

    var latitud = 0.0
    var longitud = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)
        solicitarPermisos()




            val medicamento = intent.getParcelableExtra<Medicamento>("MEDICAMENTO")
            val receta_medica_seleccionada =
                intent.getParcelableExtra<RecetaMedica>("RECETA_MEDICA_SELECCIONADA")

            val db = Firebase.firestore
            val referenciaMedicamentos = db
                .collection("receta_medica")
                .whereEqualTo("nombre_paciente", receta_medica_seleccionada?.nombre_paciente)
                .whereEqualTo("edad", receta_medica_seleccionada?.edad)
                .whereEqualTo("diagnostico", receta_medica_seleccionada?.diagnostico)
                .whereEqualTo(
                    "frecuencia_duracion_tratamiento",
                    receta_medica_seleccionada?.frecuencia_duracion_tratamiento
                )

            referenciaMedicamentos
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val referencia_documento =
                            db.collection("receta_medica").document(document.id)
                        referencia_documento.collection("medicamento")

                            .whereEqualTo("nombre_medicamento", medicamento?.nombre_medicamento)
                            .whereEqualTo("concentracion", medicamento?.concentracion)
                            .whereEqualTo("forma_farmaceutica", medicamento?.forma_farmaceutica)
                            .whereEqualTo("venta_libre", medicamento?.venta_libre)
                            .whereEqualTo("latitud", medicamento?.latitud)
                            .whereEqualTo("longitud", medicamento?.longitud)
                            .get()
                            .addOnSuccessListener { resultado ->
                                for (documento in resultado) {
                                    val latitud_medicamento = "${documento.data.get("latitud")}"
                                    val longitud_medicamento = "${documento.data.get("longitud")}"
                                    latitud = latitud_medicamento.toDouble()
                                    longitud = longitud_medicamento.toDouble()
                                }

                                val fragmentoMapa = supportFragmentManager
                                    .findFragmentById(R.id.map) as SupportMapFragment
                                fragmentoMapa.getMapAsync { googleMap ->
                                    if (googleMap != null) {
                                        mapa = googleMap
                                        establecerConfiguracionMapa()
                                        val lugar_medicamentos = LatLng(latitud, longitud)
                                        val titulo = "Lugar para retirar los medicamentos"
                                        val zoom = 17f
                                        anadirMarcador(lugar_medicamentos, titulo)
                                        moverCamaraConZoom(lugar_medicamentos, zoom)
                                    }
                                }

                    }
                }
        }
    }

    fun solicitarPermisos() {
        val contexto = this.applicationContext
        val permisosFineLocation = ContextCompat
            .checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
        if (tienePermisos) {
            permisos = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf( //Arreglo Permisos
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1 //Codigo de peticion de los permisos
            )
        }
    }

    fun anadirMarcador(latLng: LatLng, title: String) {
        mapa.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
        )
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10f) {
        mapa.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latLng, zoom)
        )
    }

    fun establecerConfiguracionMapa() {
        val contexto = this.applicationContext
        with(mapa) {
            val permisosFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos) {
                mapa.isMyLocationEnabled = true // no tenemos aun permisos
            }
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true // no tenemos aun permisos
        }
    }
}