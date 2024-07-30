package com.example.deber_03

import android.media.Image
import android.os.Parcel
import android.os.Parcelable

class CExploreItem(
    var left: Int,
    var right: Int
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(left)
        parcel.writeInt(right)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CExploreItem> {
        override fun createFromParcel(parcel: Parcel): CExploreItem {
            return CExploreItem(parcel)
        }

        override fun newArray(size: Int): Array<CExploreItem?> {
            return arrayOfNulls(size)
        }
    }

}