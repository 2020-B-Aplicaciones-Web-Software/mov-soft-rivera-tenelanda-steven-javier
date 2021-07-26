package com.example.moviles_software_2021_a

import android.os.Parcel
import android.os.Parcelable

class RecetaMedica (
    var receta_id: Int,
    var nombre: String?,
    var diagnostico: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(receta_id)
        parcel.writeString(nombre)
        parcel.writeString(diagnostico)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecetaMedica> {
        override fun createFromParcel(parcel: Parcel): RecetaMedica {
            return RecetaMedica(parcel)
        }

        override fun newArray(size: Int): Array<RecetaMedica?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "Id:${receta_id}  Nombre:${nombre}  Diagnostico:${diagnostico}"
    }
}