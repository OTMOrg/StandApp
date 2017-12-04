package com.vetkoli.sanket.standapp.application

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp
import com.vetkoli.sanket.standapp.communication.BusProvider
import com.vetkoli.sanket.standapp.communication.EventManager

/**
 * Created by Sanket on 4/12/17.
 */

class App : Application() {

    private val bus = BusProvider.instance

    override fun onCreate() {
        super.onCreate()
        appContext = this

        init()
    }

    private fun init() {
        initFirebase()
        initCommunication()
    }

    private fun initFirebase() {
        FirebaseApp.initializeApp(this)
    }

    private fun initCommunication() {
        val eventManager = EventManager(this, bus)
        bus.register(eventManager)
        bus.register(this)
    }

    companion object {
        @JvmStatic lateinit var appContext: Context
    }
}

