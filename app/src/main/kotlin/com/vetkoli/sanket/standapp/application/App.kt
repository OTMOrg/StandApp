package com.vetkoli.sanket.standapp.application

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp
import com.vetkoli.sanket.standapp.models.UpdatedByMetadata

/**
 * Created by Sanket on 4/12/17.
 */

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        appContext = this

        init()
    }

    private fun init() {
        initFirebase()
    }

    private fun initFirebase() {
        FirebaseApp.initializeApp(this)
    }

    companion object {
        @JvmStatic lateinit var appContext: Context
        lateinit var missMap: MutableMap<String, MutableMap<String, MutableMap<String, UpdatedByMetadata>>>
    }
}

