package com.vetkoli.sanket.standapp.login.ui.activities

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.credentials.Credential
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig
import com.google.android.gms.auth.api.credentials.HintRequest
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity
import com.vetkoli.sanket.standapp.home.ui.activities.HomeActivity
import com.vetkoli.sanket.standapp.login.contract.ILoginContract
import com.vetkoli.sanket.standapp.login.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*




class LoginActivity : BaseActivity(), ILoginContract.LoginView, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private val presenter: ILoginContract.LoginPresenter
            by unsafeLazy { LoginPresenter(this) }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            return intent
        }

        private const val RC_HINT_REQUEST: Int = 1001
    }

    /***
     * Lifecycle
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.vetkoli.sanket.standapp.R.layout.activity_login)

        init()
    }

    private fun init() {
        initActionListeners()
        initOnClickListeners()
        initEmailHintRequest()
    }

    private fun initActionListeners() {
        etEmail.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                etPassword.requestFocus()
                return@setOnEditorActionListener true;
            }
             false
        }
        etPassword.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    delegateFlow()
                    true
                }
                else -> false
            }
        }
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

    private fun initEmailHintRequest() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        val googleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(Auth.CREDENTIALS_API)
                .build()

        val hintRequest = HintRequest.Builder()
                .setHintPickerConfig(
                        CredentialPickerConfig.Builder()
                                .setShowCancelButton(true)
                                .setPrompt(CredentialPickerConfig.Prompt.SIGN_IN)
                                .build()
                )
                .setEmailAddressIdentifierSupported(true)
//                .setPhoneNumberIdentifierSupported(true)
                .build()

        val intent = Auth.CredentialsApi.getHintPickerIntent(googleApiClient, hintRequest)
        try {
            startIntentSenderForResult(intent.intentSender, Companion.RC_HINT_REQUEST, null, 0, 0, 0)
        } catch (e: IntentSender.SendIntentException) {
            emailHintRequestFailure(e)
        }
    }

    private fun emailHintRequestFailure(e: IntentSender.SendIntentException) {
        snack(getString(R.string.error_suggesting_email_hint) + e.localizedMessage)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            RC_HINT_REQUEST -> handleEmailHintRequestResolution(resultCode, data)
        }
    }

    private fun handleEmailHintRequestResolution(resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_CANCELED) {
            emailHintRequestCancelled()
        } else {
            emailHintRequestSuccess(data)
        }
    }

    private fun emailHintRequestCancelled() {
        // do nothing
    }

    private fun emailHintRequestSuccess(data: Intent?) {
        val credential: Credential? = data?.getParcelableExtra(Credential.EXTRA_KEY)
        credential?.let {
            setEmail(it.id)
        }
    }

    private fun setEmail(id: String) {
        etEmail.setText(id)
    }

    override fun onConnectionFailed(result: ConnectionResult) {
        toast("Google Api Client connection failed" + result.errorMessage)
    }

    override fun onConnected(p0: Bundle?) {
        toast("Google Api Client connected")
    }

    override fun onConnectionSuspended(p0: Int) {
        toast("Google Api Client connection suspended")
    }

    /**
     * Contract
     */

    override fun snack(message: String, duration: Int, buttonString: String, listener: View.OnClickListener?) {
        showSnack(message, duration, buttonString, listener, parentContainer)
    }

    override fun showEmailEmptyError() {
        val errorString: String = getString(com.vetkoli.sanket.standapp.R.string.error_empty_email)
        etEmail.error = errorString
        toast(errorString)
    }

    override fun showPasswordEmptyError() {
        val errorString = getString(com.vetkoli.sanket.standapp.R.string.error_password_empty)
        etPassword.error = errorString
        toast(errorString)
    }

    override fun goToHomeActivity() {
        startActivity(HomeActivity.newIntent(this))
        finish()
    }

}
