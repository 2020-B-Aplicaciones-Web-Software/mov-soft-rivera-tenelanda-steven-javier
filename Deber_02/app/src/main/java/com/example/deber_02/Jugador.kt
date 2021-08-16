package com.example.deber_02

import android.os.Parcel
import android.os.Parcelable

class Jugador (
    val nickname: String?,
    val nombre_juego: String?,
    val url_imagen: String?,
    val mensaje: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nickname)
        parcel.writeString(nombre_juego)
        parcel.writeString(url_imagen)
        parcel.writeString(mensaje)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Jugador> {
        override fun createFromParcel(parcel: Parcel): Jugador {
            return Jugador(parcel)
        }

        override fun newArray(size: Int): Array<Jugador?> {
            return arrayOfNulls(size)
        }
    }
}