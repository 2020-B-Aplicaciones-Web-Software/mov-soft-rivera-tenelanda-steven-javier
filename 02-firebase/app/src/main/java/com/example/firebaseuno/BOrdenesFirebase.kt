package com.example.firebaseuno

class BOrdenesFirebase (
    val nombre: String,
    val precio: Double,
    var cantidad: Int
    ) {

    override fun toString(): String {
        return "Producto: ${nombre} \n" +
                "Precio: ${precio}\n" +
                "cantidad: ${cantidad}"

    }
    }

