package com.vetkoli.sanket.standapp.login.presenter

import android.support.design.widget.Snackbar
import android.text.TextUtils
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.login.contract.ILoginContract

/**
 * Created by Sanket on 9/12/17.
 */
class LoginPresenter(private val view: ILoginContract.LoginView):
        ILoginContract.LoginPresenter {



    override fun subscribe() {

    }

    override fun unSubscribe() {

    }

    override fun validateInput(emailId: String, password: String) {
        val trimPassword = password.trim()
        if (TextUtils.isEmpty(emailId.trim())) {
            view.showEmailEmptyError()
        } else if (TextUtils.isEmpty(trimPassword)) {
            view.showPasswordEmptyError()
        } else if (trimPassword.length < 6) {
            view.snack(view.context.getString(R.string.minimum_password))
        } else {
            view.showProgress()
            val auth = FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(emailId, password)
                    .addOnCompleteListener {
                        task ->
                        performActionOnTaskResult(task, password)
                    }
        }
    }

    private fun performActionOnTaskResult(task: Task<AuthResult>, password: String) {
        view.hideProgress()
        if (!task.isSuccessful) {
            if (password.length < 6) {
                view.snack(view.context.getString(R.string.minimum_password))
            } else {
                view.snack(view.context.getString(R.string.auth_failed), duration = Snackbar.LENGTH_LONG)
            }
        } else {
            view.toast("Start home activity")
        }
    }


}