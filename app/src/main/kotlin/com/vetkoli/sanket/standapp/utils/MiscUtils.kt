package com.vetkoli.sanket.standapp.utils

import android.content.Context
import android.net.ConnectivityManager
import com.vetkoli.sanket.standapp.application.App
import com.vetkoli.sanket.standapp.application.Constants
import com.vetkoli.sanket.standapp.models.Member
import java.util.*

/**
 * Created by Sanket on 4/12/17.
 */

object MiscUtils {

    val isConnectedToInternet: Boolean
        get() {
            val cm = App.appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }

    fun getMissCountThisMonth(member: Member): Int {
        val instance = Calendar.getInstance()
        instance.timeInMillis = System.currentTimeMillis()
        val missMap = member.missMap
        val year = "Id_" + instance.get(Calendar.YEAR)
        val month = "Id_" + instance.get(Calendar.MONTH)
        if (!missMap.containsKey(year)) {
            return 0
        } else {
            val monthMap = missMap[year]
            if (monthMap == null || !monthMap.containsKey(month)) {
                return 0
            } else {
                val dayMap = monthMap[month]
                if (dayMap == null) {
                    return 0;
                } else {
                    return dayMap.size
                }
            }
        }
    }

    fun isCurrentUserScrumMaster(member: Member): Boolean {
        return member.role?.toLowerCase()?.contains(Constants.SCRUM_MASTER.toLowerCase())!!
    }

}
