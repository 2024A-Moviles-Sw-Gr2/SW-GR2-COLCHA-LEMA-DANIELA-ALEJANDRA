package com.example.materia_estudiante_dacl

import android.os.Parcel
import android.os.Parcelable

class AEstudianteEntity (
    var id:Int,
    var nombre:String,
    var semestre: Int,
    var promedio: Double,
    var materias: MutableList<Int>?
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readArrayList(Int::class.java.classLoader) as MutableList<Int>
    ) {}

    override fun toString(): String {
        return nombre
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeInt(semestre)
        parcel.writeDouble(promedio)
        parcel.writeList(materias)
    }

    override fun describeContents(): Int {
        return 0
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