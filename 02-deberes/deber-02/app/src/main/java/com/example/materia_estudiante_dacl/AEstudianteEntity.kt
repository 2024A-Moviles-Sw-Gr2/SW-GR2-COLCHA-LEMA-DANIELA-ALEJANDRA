package com.example.materia_estudiante_dacl

import android.os.Parcel
import android.os.Parcelable

class AEstudianteEntity (
    var id:Int,
    var nombre:String,
    var semestre: Int,
    var promedio: Double,
    var materia: Int,
    var ubicacion: String
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeInt(semestre)
        parcel.writeDouble(promedio)
        parcel.writeInt(materia)
        parcel.writeString(ubicacion)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return nombre
    }

    companion object CREATOR : Parcelable.Creator<AEstudianteEntity> {
        override fun createFromParcel(parcel: Parcel): AEstudianteEntity {
            return AEstudianteEntity(parcel)
        }

        override fun newArray(size: Int): Array<AEstudianteEntity?> {
            return arrayOfNulls(size)
        }
    }

}