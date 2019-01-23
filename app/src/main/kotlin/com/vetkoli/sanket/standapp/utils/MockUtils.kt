package com.vetkoli.sanket.standapp.utils

import com.vetkoli.sanket.standapp.models.Member
import java.util.*

/**
 * Created by Sanket on 21/12/17.
 */
object MockUtils {

    private val mockedMember: Member
        get() {
            val member = Member()
            member.id = UUID.randomUUID().toString()
            member.name = UUID.randomUUID().toString()
            member.profilePic = dummyPicUrl
            member.lastUpdatedOn = getRandomLong(100000000000)
            return member
        }

    //delibrately did nothing
    val dummyPicUrl: String?
        get() {
            var url: String? = null
            when (Random().nextInt(4)) {
                0 -> url = "http://i.imgur.com/DCE503d.jpg"
                1 -> url = "https://images-na.ssl-images-amazon.com/images/M/MV5BMTQ1Mzg3NTA0OF5BMl5BanBnXkFtZTcwNTgyNTM5OQ@@._V1_SY1000_CR0,0,1333,1000_AL_.jpg"
                2 -> url = "http://images2.fanpop.com/image/photos/8800000/Scarlett-Johansson-scarlett-johansson-8836765-500-375.jpg"
                3 -> {
                }
            }
            return url
        }

    private fun getRandomLong(maxVal: Long): Long {
        return Random().nextLong() % maxVal
    }

    fun getMockedMembers(size: Int): List<Member> {
        val memberList = ArrayList<Member>(size)
        for (i in 0 until size) {
            memberList.add(mockedMember)
        }
        return memberList
    }

}