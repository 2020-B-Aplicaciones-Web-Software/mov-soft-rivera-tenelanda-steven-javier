package com.example.receta_medica_medicamento

import android.os.Parcel
import android.os.Parcelable
import java.util.*
import kotlin.collections.ArrayList

class RecetaMedica (
    var id_receta: Int,
    var nombre_paciente: String?,
    var edad: Int,
    var diagnostico: String?,
    var frecuencia_duracion_tratamiento: String?,
    var medicamentos: ArrayList<Medicamento>?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        arrayListOf<Medicamento>().apply {
            parcel.readList(this,Medicamento::class.java.classLoader)
        }


    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id_receta)
        parcel.writeString(nombre_paciente)
        parcel.writeInt(edad)
        parcel.writeString(diagnostico)
        parcel.writeString(frecuencia_duracion_tratamiento)
        parcel.writeList(medicamentos)
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
        return "Id: ${id_receta} \n" +
                "Nombre: ${nombre_paciente}\n" +
                "Edad: ${edad}\n" +
                "Diagnostico: ${diagnostico}\n" +
                "Tratamiento: ${frecuencia_duracion_tratamiento}"
    }
}


