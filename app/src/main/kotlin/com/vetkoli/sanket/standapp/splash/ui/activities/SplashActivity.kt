package com.vetkoli.sanket.standapp.splash.ui.activities

import android.os.Bundle
import android.os.Handler
import android.view.View
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity
import com.vetkoli.sanket.standapp.login.ui.activities.LoginActivity
import com.vetkoli.sanket.standapp.splash.contract.ISplashContract
import com.vetkoli.sanket.standapp.splash.presenter.SplashPresenter
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Created by Sanket on 30/11/17.
 */

class SplashActivity : BaseActivity(), ISplashContract.SplashView {

//    val llParentContainer: find<LinearLayout>(R.id.parentContainer)

    private val presenter: ISplashContract.SplashPresenter by unsafeLazy { SplashPresenter(this) }

//    private lateinit var presenter: ISplashContract.SplashPresenter

    /***
     * Lifecycle
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        init()
    }

    private fun init() {
//        initPresenter()
    }

    /*private fun initPresenter() {
        presenter = SplashPresenter(this)
    }*/

    override fun onResume() {
        super.onResume()
        Handler().postDelayed(Runnable {
            presenter.delegateFlow()
        }, 3000)
    }

    /***
     * Contract
     */

    override fun goToLoginActivity() {
        startActivity(LoginActivity.newIntent(this))
        finish()
    }

    override fun goToHomeActivity() {
        toast("Go to home")
    }

    override fun snack(message: String, duration: Int, buttonString: String, listener: View.OnClickListener?) {
        showSnack(message, duration, buttonString, listener, parentContainer)
    }
}
