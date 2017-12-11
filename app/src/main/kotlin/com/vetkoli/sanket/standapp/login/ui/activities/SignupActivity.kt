package com.vetkoli.sanket.standapp.login.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity
import com.vetkoli.sanket.standapp.login.contract.ILoginContract
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity: BaseActivity(), ILoginContract.SignupView {

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
    }

    /***
     * Contract
     */

    override fun snack(message: String, duration: Int, buttonString: String, listener: View.OnClickListener?) {
        showSnack(message, duration, buttonString, listener, parentContainer)
    }
}
