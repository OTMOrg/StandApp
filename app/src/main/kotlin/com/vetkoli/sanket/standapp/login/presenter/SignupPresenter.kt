package com.vetkoli.sanket.standapp.login.presenter

import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.login.contract.ILoginContract

/**
 * Created by Sanket on 15/12/17.
 */

class SignupPresenter(private val view: ILoginContract.SignupView): ILoginContract.SignupPresenter {
    override fun validateInputAndSignUp(email: String, password: String) {
        val trimPassword = password.trim()
        if (TextUtils.isEmpty(email.trim())) {
            view.showEmailEmptyError()
        } else if (TextUtils.isEmpty(trimPassword)) {
            view.showPasswordEmptyError()
        } else if (trimPassword.length < 6) {
            view.snack(view.context.getString(R.string.minimum_password))
        } else {
            view.showProgress()
            signUp(email, password)
        }
    }

    private fun signUp(email: String, password: String) {
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            task -> takeActionBasedOnTaskResult(task)
        }
    }

    private fun takeActionBasedOnTaskResult(task: Task<AuthResult>) {
        view.hideProgress()
        if (!task.isSuccessful) {
            view.toast(message = "Authentication failed: " + task.exception,
                    duration = Toast.LENGTH_LONG)
        } else {
            view.goToHomeActivity()
        }
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {

    }


}
