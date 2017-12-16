package com.vetkoli.sanket.standapp.models

/**
 * Created by Sanket on 16/12/17.
 */
class Member {

    var name: String? = null

    var profilePic: String? = null

    lateinit var id: String

    var lastUpdatedOn: Long = 0

    val missList: List<Long>? = null

}