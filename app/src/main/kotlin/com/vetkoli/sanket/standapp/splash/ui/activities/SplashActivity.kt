package com.vetkoli.sanket.standapp.splash.ui.activities

import android.os.Bundle
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity
import com.vetkoli.sanket.standapp.splash.contract.ISplashContract
import com.vetkoli.sanket.standapp.splash.presenter.SplashPresenter

/**
 * Created by Sanket on 30/11/17.
 */

class SplashActivity : BaseActivity(), ISplashContract.SplashView {
    /*override fun snack(message: String) {
        val snackbar = Snackbar.make(parentContainer, message, Snackbar.LENGTH_SHORT)
        snackbar.setAction("OK", View.OnClickListener { snackbar.dismiss() })
        snackbar.show()
    }*/

    lateinit var presenter: ISplashContract.SplashPresenter

    /***
     * Lifecycle
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        init()
    }

    private fun init() {
        initPresenter()
    }

    private fun initPresenter() {
        presenter = SplashPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.delegateFlow()
    }

    /***
     * Contract
     */

    override fun goToLoginActivity() {
        toast("Go to login")
    }

    override fun goToHomeActivity() {
        toast("Go to home")
    }
}
