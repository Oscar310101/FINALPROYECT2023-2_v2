package com.example.finalproyect2023_2.ENTITIES

import java.security.Timestamp


import android.os.Parcel
import android.os.Parcelable

data class JobOffer(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    var timestamp: com.google.firebase.Timestamp? = null,
    var postulaciones: MutableList<Postulacion> = mutableListOf(),
    var fechaPublicacion : String="" ,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readParcelable(com.google.firebase.Timestamp::class.java.classLoader),
        mutableListOf<Postulacion>().apply {
            parcel.readList(this, Postulacion::class.java.classLoader)
        }
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeParcelable(timestamp, flags)
        parcel.writeTypedList(postulaciones)
        parcel.writeString(fechaPublicacion)
    }

    fun agregarPostulacion(postulacion: Postulacion) {
        postulaciones.add(postulacion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<JobOffer> {
        override fun createFromParcel(parcel: Parcel): JobOffer {
            return JobOffer(parcel)
        }

        override fun newArray(size: Int): Array<JobOffer?> {
            return arrayOfNulls(size)
        }
    }
}
