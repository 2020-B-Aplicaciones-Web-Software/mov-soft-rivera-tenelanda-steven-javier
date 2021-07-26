package com.example.receta_medica_medicamento

import android.os.Parcel
import android.os.Parcelable

class Medicamento (
    val id_medicamento: Int,
    val nombre_medicamento: String?,
    val concentracion: Double,
    val forma_farmaceutica: String?,
    val venta_libre: Boolean,
    val id_receta_medica: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id_medicamento)
        parcel.writeString(nombre_medicamento)
        parcel.writeDouble(concentracion)
        parcel.writeString(forma_farmaceutica)
        parcel.writeByte(if (venta_libre) 1 else 0)
        parcel.writeInt(id_receta_medica)
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
        return "Id: ${id_medicamento} \n" +
                "Nombre del medicamento: ${nombre_medicamento}\n" +
                "Concentracion del medicamento: ${concentracion}\n" +
                "Forma farmaceutica: ${forma_farmaceutica}\n" +
                "Es de venta libre: ${venta_libre}\n"
    }
}