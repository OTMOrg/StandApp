package com.vetkoli.sanket.standapp.home

import android.arch.lifecycle.ViewModel

/**
 * Created by Sanket on 10/10/18.
 */
class TimerViewModel : ViewModel() {

    var startTime: Long = 0

    var isStarted: Boolean = false

    var isSmallBeepPlayed: Boolean = false

    var isNearEndBeepPlayed: Boolean = false

    fun reset() {
        startTime = 0
        isNearEndBeepPlayed = false
        isSmallBeepPlayed = false
        isStarted = false
    }
}
