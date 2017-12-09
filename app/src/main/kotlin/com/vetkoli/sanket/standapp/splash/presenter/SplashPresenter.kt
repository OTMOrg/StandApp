package com.vetkoli.sanket.standapp.splash.presenter

import android.support.design.widget.Snackbar
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.splash.contract.ISplashContract
import com.vetkoli.sanket.standapp.splash.ui.activities.SplashActivity
import com.vetkoli.sanket.standapp.utils.MiscUtils

/**
 * Created by Sanket on 4/12/17.
 */

class SplashPresenter(private val view: ISplashContract.SplashView) : ISplashContract.SplashPresenter {

    override fun subscribe() {

    }

    override fun unSubscribe() {

    }

    override fun delegateFlow() {
        if (MiscUtils.isConnectedToInternet) {
            val auth = FirebaseAuth.getInstance()
            if (auth.currentUser != null) {
                view.goToHomeActivity()
            } else {
                view.goToLoginActivity()
            }
        } else {
            view.snack(view.context.getString(R.string.error_no_internet),
                    duration = Snackbar.LENGTH_INDEFINITE,
                    listener = View.OnClickListener {
                        (view.context as SplashActivity).finish()
                    }
            )
//            view.snack("Hello")
        }
    }
}
