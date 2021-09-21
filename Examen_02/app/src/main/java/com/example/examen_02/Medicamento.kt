package com.example.examen_02

import android.os.Parcel
import android.os.Parcelable

class Medicamento (
    val nombre_medicamento: String?,
    val concentracion: Double,
    val forma_farmaceutica: String?,
    val venta_libre: Boolean,
    ): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre_medicamento)
        parcel.writeDouble(concentracion)
        parcel.writeString(forma_farmaceutica)
        parcel.writeByte(if (venta_libre) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Medicamento> {
        override fun createFromParcel(parcel: Parcel): Medicamento {
            return Medicamento(parcel)
        }

        override fun newArray(size: Int): Array<Medicamento?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "Nombre del medicamento: ${nombre_medicamento}\n" +
                "Concentracion del medicamento: ${concentracion}\n" +
                "Forma farmaceutica: ${forma_farmaceutica}\n" +
                "Es de venta libre: ${venta_libre}\n"
    }

}