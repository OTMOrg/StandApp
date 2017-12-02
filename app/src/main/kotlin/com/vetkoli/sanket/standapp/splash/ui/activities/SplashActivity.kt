package com.vetkoli.sanket.standapp.splash.ui.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.LinearLayout

import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity
import kotterknife.bindView

/**
 * Created by Sanket on 30/11/17.
 */

class SplashActivity : BaseActivity() {

    val parentContainer: LinearLayout by bindView<LinearLayout>(R.id.parent_container)

    override fun showError(message: String) {
        Snackbar.make(parentContainer, message, Snackbar.LENGTH_SHORT)
                .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        showError("Test")
    }
}
