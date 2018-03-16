package com.vetkoli.sanket.standapp.models

import android.os.Parcel
import android.os.Parcelable
import com.vetkoli.sanket.standapp.base.models.Item

/**
 * Created by Sanket on 16/12/17.
 */
class Member() : Item, Parcelable {

    var name: String? = null

    var profilePic: String? = null

    lateinit var id: String

    var lastUpdatedOn: Long = 0

    var missCount: Int = 0

    var lastUpdatedBy: String? = null

    var role: String? = null

    var missMap: MutableMap<String, MutableMap<String, MutableMap<String, UpdatedByMetadata>>> = mutableMapOf()

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        profilePic = parcel.readString()
        id = parcel.readString()
        lastUpdatedOn = parcel.readLong()
        missCount = parcel.readInt()
        lastUpdatedBy = parcel.readString()
        role = parcel.readString()
//        missMap = readMapFromParcel(parcel)
    }

    private fun readMapFromParcel(parcel: Parcel): MutableMap<String, MutableMap<String, MutableMap<String, UpdatedByMetadata>>> {
        val size = parcel.readInt()
        val map = mutableMapOf<String, MutableMap<String, MutableMap<String, UpdatedByMetadata>>>()
        for (i in 0 until size) {
            val key2: String = parcel.readString()
            val size2 = parcel.readInt()
            val map2 = mutableMapOf<String, MutableMap<String, UpdatedByMetadata>>()
            for (j in 0 until size2) {
                val key3: String = parcel.readString()
                val size3 = parcel.readInt()
                val map3 = mutableMapOf<String, UpdatedByMetadata>()
                for (k in 0 until size3) {
                    val key4: String = parcel.readString()
                    val value = UpdatedByMetadata.createFromParcel(parcel)
                    map3.put(key4, value)
                }
                map2.put(key3, map3)
            }
            map.put(key2, map2)
        }
        return map
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(profilePic)
        parcel.writeString(id)
        parcel.writeLong(lastUpdatedOn)
        parcel.writeInt(missCount)
        parcel.writeString(lastUpdatedBy)
        parcel.writeString(role)
//        writeMapToParcel(parcel, flags, missMap)
    }

    private fun writeMapToParcel(parcel: Parcel, flags: Int,
                                 missMap: MutableMap<String,
                                         MutableMap<String,
                                                 MutableMap<String, UpdatedByMetadata>>>) {
        parcel.writeInt(missMap.size)
        for (entry in missMap.entries) {
            parcel.writeString(entry.key)
            parcel.writeInt(entry.value.size)
            for (entry2 in entry.value.entries) {
                parcel.writeString(entry2.key)
                parcel.writeInt(entry2.value.size)
                for (entry3 in entry2.value.entries){
                    parcel.writeString(entry3.key)
                    parcel.writeParcelable(entry3.value, flags)
                }
            }
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Member> {
        override fun createFromParcel(parcel: Parcel): Member {
            return Member(parcel)
        }

        override fun newArray(size: Int): Array<Member?> {
            return arrayOfNulls(size)
        }
    }

}