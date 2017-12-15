package com.vetkoli.sanket.standapp.login.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity
import com.vetkoli.sanket.standapp.login.contract.ILoginContract
import com.vetkoli.sanket.standapp.login.presenter.SignupPresenter
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity: BaseActivity(), ILoginContract.SignupView {

    private val presenter : ILoginContract.SignupPresenter
            by unsafeLazy { SignupPresenter(this) }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, SignupActivity::class.java)
            return intent
        }
    }

    /***
     * Lifecycle
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        init()
    }

    private fun init() {
        initOnClickListener()
    }

    private fun initOnClickListener() {
        btnSignUp.setOnClickListener { delegateFlow() }
    }

    private fun delegateFlow() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        presenter.validateInputAndSignUp(email, password)
    }

    /***
     * Contract
     */

    override fun snack(message: String, duration: Int, buttonString: String, listener: View.OnClickListener?) {
        showSnack(message, duration, buttonString, listener, parentContainer)
    }

    override fun showEmailEmptyError() {
        etEmail.error = getString(R.string.error_empty_email)
    }

    override fun showPasswordEmptyError() {
        etPassword.error = getString(R.string.error_password_empty)
    }

    override fun showProgress() {
        toast("Show Progress")
    }

    override fun hideProgress() {
        toast("Hide Progress")
    }

    override fun goToHomeActivity() {
        toast("Go to main activity")
    }
}
