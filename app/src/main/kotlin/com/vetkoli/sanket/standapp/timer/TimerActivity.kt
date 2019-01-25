package com.vetkoli.sanket.standapp.timer

import android.app.Dialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity
import com.vetkoli.sanket.standapp.utils.setTimer
import kotlinx.android.synthetic.main.activity_timer.*


class TimerActivity : BaseActivity() {

    private val timerViewModel: TimerViewModel by lazy(LazyThreadSafetyMode.NONE) { ViewModelProviders.of(this).get(TimerViewModel::class.java) }
    private val defaultStandupTimeMillis = 15 * 60 * 1000L
    private val defaultPerPersonMillis = 2 * 60 * 1000L
//    private val defaultPerPersonMillis = 2 * 60 * 100L

    private var perPersonMillis = defaultPerPersonMillis
    private var timer: CountDownTimer? = null

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, TimerActivity::class.java)
        }
    }

    /***
     * Lifecycle
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        init()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        timer?.cancel()
        super.onDestroy()
    }

    /***
     * Helper
     */

    private fun init() {
        initViews()
        initOnClickListeners()
    }

    private fun initViews() {
//        showTimeChoosingDialog()
        initStartStopBtnText()
        initChronometer()
        initCountDownTimer()
    }

    private fun initCountDownTimer() {
        tvCountdownTimer.setTimer(perPersonMillis)
    }

    private fun startCountdownTimer() {
        timer?.cancel()
        timer = object : CountDownTimer(perPersonMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvCountdownTimer.setTimer(millisUntilFinished)
                if (millisUntilFinished * 2 < perPersonMillis && !timerViewModel.isSmallBeepPlayed) {
                    playSmallBeep()
                    timerViewModel.isSmallBeepPlayed = true
                }
            }
            override fun onFinish() {
                tvCountdownTimer.setTimer(0)
                playLongBeep()
            }
        }
        (timer as CountDownTimer).start()
    }

    private fun showTimeChoosingDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_timer)
        dialog.setTitle(getString(R.string.select_per_person_duration))
        val numberPicker =  dialog.findViewById<NumberPicker>(R.id.numberPicker)
        val tvTitle = dialog.findViewById<TextView>(R.id.tvTitle)
        val btnOkay = dialog.findViewById<Button>(R.id.btnOkay)
        btnOkay.setOnClickListener {
            toast("Per person time updated!")
            perPersonMillis = numberPicker.value * 60 * 1000L
            initCountDownTimer()
            dialog.dismiss()
        }
        tvTitle.setText(R.string.select_per_person_duration)
        numberPicker.maxValue = 60
        numberPicker.minValue = 1
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
    }

    private fun initChronometer() {
        if (timerViewModel.isStarted) {
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
        tvCountdownTimer.setOnClickListener {
            if (timerViewModel.isStarted) {
                startCountdownTimer()
            } else {
                showTimeChoosingDialog()
            }
        }
    }

    private fun playLongBeep() {
//        snack("Play long beep")
        val toneG = ToneGenerator(AudioManager.STREAM_ALARM, 100)
        toneG.startTone(ToneGenerator.TONE_DTMF_0, 1000)
    }

    private fun playSmallBeep() {
//        snack("Play small beep")
        val toneG = ToneGenerator(AudioManager.STREAM_ALARM, 100)
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD)
    }

    private fun onStartStopBtnClick() {
        if (timerViewModel.isStarted) {
            chronometer2.stop()
            timer?.cancel()
        } else {
            timerViewModel.startTime = SystemClock.elapsedRealtime()
            chronometer2.base = timerViewModel.startTime
            chronometer2.start()
            startCountdownTimer()
        }
        timerViewModel.isStarted = !timerViewModel.isStarted
        initStartStopBtnText()
    }

    /***
     * Contract
     */

    override fun snack(message: String, duration: Int, buttonString: String, listener: View.OnClickListener?) {
        showSnack(message, duration, buttonString, listener, parentContainer)
    }

}
