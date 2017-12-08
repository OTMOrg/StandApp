package com.vetkoli.sanket.standapp.splash.ui.activities

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.LinearLayout
import butterknife.BindView
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity
import com.vetkoli.sanket.standapp.login.LoginActivity
import com.vetkoli.sanket.standapp.splash.contract.ISplashContract
import com.vetkoli.sanket.standapp.splash.presenter.SplashPresenter

/**
 * Created by Sanket on 30/11/17.
 */

class SplashActivity : BaseActivity(), ISplashContract.SplashView {

    @BindView(R.id.parent_container)
    lateinit var llParentContainer: LinearLayout

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
        Handler().postDelayed(Runnable {
            presenter.delegateFlow()
        }, 3000)
    }

    /***
     * Contract
     */

    override fun goToLoginActivity() {
        startActivity(LoginActivity.newIntent(this))
    }

    override fun goToHomeActivity() {
        toast("Go to home")
    }

    override fun snack(message: String, duration: Int, buttonString: String, listener: View.OnClickListener?) {
        val snackbar = Snackbar.make(llParentContainer, message, duration)
        snackbar.setAction(buttonString, listener)
        snackbar.show()
    }
}
