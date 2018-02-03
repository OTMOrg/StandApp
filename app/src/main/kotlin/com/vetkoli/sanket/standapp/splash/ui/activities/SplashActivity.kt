package com.vetkoli.sanket.standapp.splash.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity
import com.vetkoli.sanket.standapp.home.ui.activities.HomeActivity
import com.vetkoli.sanket.standapp.login.ui.activities.LoginActivity
import com.vetkoli.sanket.standapp.splash.contract.ISplashContract
import com.vetkoli.sanket.standapp.splash.presenter.SplashPresenter
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Created by Sanket on 30/11/17.
 */

class SplashActivity : BaseActivity(), ISplashContract.SplashView {

    private val presenter: ISplashContract.SplashPresenter by unsafeLazy { SplashPresenter(this) }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, SplashActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            return intent
        }
    }

    /***
     * Lifecycle
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

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
        startActivity(HomeActivity.newIntent(this))
        finish()
    }

    override fun snack(message: String, duration: Int, buttonString: String, listener: View.OnClickListener?) {
        showSnack(message, duration, buttonString, listener, parentContainer)
    }
}
