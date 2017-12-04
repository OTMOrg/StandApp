package com.vetkoli.sanket.standapp.utils

import android.content.Context
import android.net.ConnectivityManager
import com.vetkoli.sanket.standapp.application.App

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

}
