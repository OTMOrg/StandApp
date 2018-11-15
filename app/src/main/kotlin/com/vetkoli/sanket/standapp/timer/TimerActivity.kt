package com.vetkoli.sanket.standapp.timer

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity
import com.vetkoli.sanket.standapp.home.TimerViewModel
import kotlinx.android.synthetic.main.activity_timer.*


class TimerActivity : BaseActivity() {

    private val timerViewModel: TimerViewModel by lazy(LazyThreadSafetyMode.NONE) { ViewModelProviders.of(this).get(TimerViewModel::class.java) }
//    private val warningThreshold = 60000L
    private val warningThreshold = 6000L
//    private val nearEndThreshold = 110000L
    private val nearEndThreshold = 11000L

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, TimerActivity::class.java)
            return intent
        }
    }

    /***
     * Lifecycle
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        init()
    }

    /***
     * Helper
     */

    private fun init() {
        initViews()
        initOnClickListeners()
    }

    /***
     * Contract
     */

    private fun initViews() {
        initStartStopBtnText()
        if (timerViewModel.isStarted) {
            chronometer.base = SystemClock.elapsedRealtime() - timerViewModel.startTime
            chronometer.start()
            chronometer2.base = SystemClock.elapsedRealtime() - timerViewModel.startTime
            chronometer2.start()
        }
    }

    private fun initStartStopBtnText() {
        if (!timerViewModel.isStarted) {
            btnStartStop.setText(R.string.start)
        } else {
            btnStartStop.setText(R.string.stop)
        }
    }

    private fun initOnClickListeners() {
        btnStartStop.setOnClickListener {
            onStartStopBtnClick()
        }
        chronometer.setOnChronometerTickListener {
            val elapsedTime = SystemClock.elapsedRealtime() - it.base;
            if (elapsedTime > warningThreshold && !timerViewModel.isSmallBeepPlayed) {
                timerViewModel.isSmallBeepPlayed = true
                playSmallBeep()
            } else if (elapsedTime > nearEndThreshold && !timerViewModel.isNearEndBeepPlayed) {
                timerViewModel.isNearEndBeepPlayed = true
                playLongBeep()
            }
        }
    }

    private fun playLongBeep() {
        snack("Play long beep")
        val toneG = ToneGenerator(AudioManager.STREAM_ALARM, 100)
        toneG.startTone(ToneGenerator.TONE_DTMF_0, 2000)
    }

    private fun playSmallBeep() {
        snack("Play small beep")
        val toneG = ToneGenerator(AudioManager.STREAM_ALARM, 100)
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD)
    }

    private fun onStartStopBtnClick() {
        if (timerViewModel.isStarted) {
            chronometer.stop()
            chronometer2.stop()
        } else {
            timerViewModel.startTime = SystemClock.elapsedRealtime()
            chronometer.base = timerViewModel.startTime
            chronometer2.base = timerViewModel.startTime
            chronometer.start()
            chronometer2.start()
        }
        timerViewModel.isStarted = !timerViewModel.isStarted
        initStartStopBtnText()
    }

    override fun snack(message: String, duration: Int, buttonString: String, listener: View.OnClickListener?) {
        showSnack(message, duration, buttonString, listener, parentContainer)
    }

}
