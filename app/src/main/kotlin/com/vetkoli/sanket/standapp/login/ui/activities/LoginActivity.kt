package com.vetkoli.sanket.standapp.login.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity
import com.vetkoli.sanket.standapp.home.ui.activities.HomeActivity
import com.vetkoli.sanket.standapp.login.contract.ILoginContract
import com.vetkoli.sanket.standapp.login.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), ILoginContract.LoginView {

    private val presenter: ILoginContract.LoginPresenter
            by unsafeLazy { LoginPresenter(this) }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            return intent
        }
    }

    /***
     * Lifecycle
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    private fun init() {
        initActionListeners()
        initOnClickListeners()
    }

    private fun initActionListeners() {
        etEmail.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                etPassword.requestFocus()
                return@setOnEditorActionListener true;
            }
             false
        }
        etPassword.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                return when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        delegateFlow()
                        true
                    }
                    else -> false
                }
            }
        })
    }

    private fun initOnClickListeners() {
        btnLogin.setOnClickListener { delegateFlow() }
        btnSignup.setOnClickListener { goToSignupActivity() }
    }

    private fun goToSignupActivity() {
        startActivity(SignupActivity.newIntent(this))
    }

    private fun delegateFlow() {
        val emailId: String = etEmail.text.toString()
        val password: String = etPassword.text.toString()
        presenter.validateInputAndSignIn(emailId, password)
    }

    /**
     * Contract
     */

    override fun snack(message: String, duration: Int, buttonString: String, listener: View.OnClickListener?) {
        showSnack(message, duration, buttonString, listener, parentContainer)
    }

    override fun showEmailEmptyError() {
        val errorString: String = getString(R.string.error_empty_email)
        etEmail.error = errorString
        toast(errorString)
    }

    override fun showPasswordEmptyError() {
        val errorString = getString(R.string.error_password_empty)
        etPassword.error = errorString
        toast(errorString)
    }

    override fun goToHomeActivity() {
        startActivity(HomeActivity.newIntent(this))
        finish()
    }

}
