package com.vetkoli.sanket.standapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity

class ResetPasswordActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, ResetPasswordActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
    }

    override fun snack(message: String, duration: Int, buttonString: String, listener: View.OnClickListener?) {

    }
}
