package com.example.finalproyect2023_2.ENTITIES

import java.util.*

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Postulacion(
    val idPostulacion: String,
    val nombre: String,
    val fechaPostulacion: String,
    val detalles: String,
    val idOferta: String,
    val idPostulante: String
) : Parcelable {

    constructor() : this("", "", "", "", "", "")

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idPostulacion)
        parcel.writeString(nombre)
        parcel.writeString(fechaPostulacion)
        parcel.writeString(detalles)
        parcel.writeString(idOferta)
        parcel.writeString(idPostulante)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Postulacion> {
        override fun createFromParcel(parcel: Parcel): Postulacion {
            return Postulacion(parcel)
        }

        override fun newArray(size: Int): Array<Postulacion?> {
            return arrayOfNulls(size)
        }
    }
}
