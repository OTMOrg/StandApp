package com.vetkoli.sanket.standapp.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Sanket on 21/2/18.
 */
class UpdatedByMetadata() : Parcelable {

    var updatedAt: Long = 0

    var updatedByName: String = ""

    var updatedById: String = ""

    constructor(parcel: Parcel) : this() {
        updatedAt = parcel.readLong()
//        updatedByName = parcel.readString()
//        updatedById = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(updatedAt)
//        parcel.writeString(updatedByName)
//        parcel.writeString(updatedById)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UpdatedByMetadata> {
        override fun createFromParcel(parcel: Parcel): UpdatedByMetadata {
            return UpdatedByMetadata(parcel)
        }

        override fun newArray(size: Int): Array<UpdatedByMetadata?> {
            return arrayOfNulls(size)
        }
    }

}