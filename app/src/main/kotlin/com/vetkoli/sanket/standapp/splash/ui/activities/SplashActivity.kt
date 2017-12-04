package com.vetkoli.sanket.standapp.splash.ui.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity

/**
 * Created by Sanket on 30/11/17.
 */

class SplashActivity : BaseActivity() {

    /*override fun snack(message: String) {
        val snackbar = Snackbar.make(parentContainer, message, Snackbar.LENGTH_SHORT)
        snackbar.setAction("OK", View.OnClickListener { snackbar.dismiss() })
        snackbar.show()
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        snack("Hello", Snackbar.LENGTH_INDEFINITE)
    }
}
