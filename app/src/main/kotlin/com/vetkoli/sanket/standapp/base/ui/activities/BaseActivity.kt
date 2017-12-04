package com.vetkoli.sanket.standapp.base.ui.activities

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.contract.IBaseContract
import kotterknife.bindView

/**
 * Created by Sanket on 30/11/17.
 */

abstract class BaseActivity : AppCompatActivity() , IBaseContract.BaseView {

    val parentContainer: LinearLayout by bindView<LinearLayout>(R.id.parent_container)

    override val context: Context
        get() = this

    override fun toast(message: String) {
        toast(message, Toast.LENGTH_SHORT)
    }

    override fun toast(message: String, duration: Int) {
        Toast.makeText(this, message, duration).show()
    }

    override fun snack(message: String) {
        snack(message, Snackbar.LENGTH_SHORT)
    }

    override fun snack(message: String, duration: Int) {
        Snackbar.make(parentContainer, message, duration).show()
    }

    override fun snack(message: String, duration: Int, listener: View.OnClickListener) {
        snack(message, duration, getString(R.string.ok), listener)
    }

    override fun snack(message: String, duration: Int, buttonString: String) {
        val snackbar = Snackbar.make(parentContainer, message, duration)
        snackbar.setAction(buttonString, { snackbar.dismiss() })
        snackbar.show()
    }

    override fun snack(message: String, duration: Int, buttonString: String, listener: View.OnClickListener) {
        Snackbar.make(parentContainer, message, duration)
                .setAction(buttonString, listener)
                .show()
    }

}
