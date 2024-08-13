package com.example.materia_estudiante_dacl

import android.os.Parcel
import android.os.Parcelable

class BMateriasEntity(
    var id: Int,
    var nombre: String,
    var descripcion: String
):Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun toString(): String {
        return nombre
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BMateriasEntity> {
        override fun createFromParcel(parcel: Parcel): BMateriasEntity {
            return BMateriasEntity(parcel)
        }

        override fun newArray(size: Int): Array<BMateriasEntity?> {
            return arrayOfNulls(size)
        }
    }
}