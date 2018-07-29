package com.vetkoli.sanket.standapp.base.ui.activities

import android.app.ProgressDialog
import android.content.Context
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.vetkoli.sanket.standapp.base.contract.IBaseContract

/**
 * Created by Sanket on 30/11/17.
 */

abstract class BaseActivity : AppCompatActivity() , IBaseContract.BaseView {

    val progressDialog: ProgressDialog by unsafeLazy { ProgressDialog(this) }

    override val context: Context
        get() = this

    override fun toast(message: String, duration: Int) {
        Toast.makeText(this, message, duration).show()
    }

    abstract override fun snack(message: String, duration: Int, buttonString: String, listener: View.OnClickListener?)

    protected fun showSnack(message: String, duration: Int, buttonString: String, listener: View.OnClickListener?, view: View) {
        val snackbar = Snackbar.make(view, message, duration)
        snackbar.setAction(buttonString, listener)
        snackbar.show()
    }

    override fun showProgress(message: String) {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
        progressDialog.apply {
            setMessage(message)
            show()
        }
    }

    fun showProgress(@StringRes id: Int) {
        showProgress(getString(id))
    }

    override fun hideProgress() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    protected fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)

}
