package com.example.deber_03

import android.media.Image
import android.os.Parcel
import android.os.Parcelable

class BPlaylist(
    var imageResource: Int,
    var nombre: String,
    var descripcion: String
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imageResource)
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BPlaylist> {
        override fun createFromParcel(parcel: Parcel): BPlaylist {
            return BPlaylist(parcel)
        }

        override fun newArray(size: Int): Array<BPlaylist?> {
            return arrayOfNulls(size)
        }
    }
}