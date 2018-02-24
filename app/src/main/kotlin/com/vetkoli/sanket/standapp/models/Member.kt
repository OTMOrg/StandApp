package com.vetkoli.sanket.standapp.models

import com.vetkoli.sanket.standapp.base.models.Item

/**
 * Created by Sanket on 16/12/17.
 */
class Member: Item {

    var name: String? = null

    var profilePic: String? = null

    lateinit var id: String

    var lastUpdatedOn: Long = 0


    // Default constructor required for calls to DataSnapshot.getValue(Member.class)
    constructor()

    var missCount: Int = 0

    var lastUpdatedBy: String? = null

    var role: String? = null

    var missMap: MutableMap<String, MutableMap<String, MutableMap<String, UpdatedByMetadata>>> = mutableMapOf()

}