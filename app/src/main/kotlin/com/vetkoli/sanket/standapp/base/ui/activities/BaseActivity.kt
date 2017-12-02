package com.vetkoli.sanket.standapp.base.ui.activities

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.contract.IBaseContract
import kotterknife.bindView

/**
 * Created by Sanket on 30/11/17.
 */

abstract class BaseActivity : AppCompatActivity() , IBaseContract.BaseView {

    val parentContainer: LinearLayout by bindView<LinearLayout>(R.id.parent_container)

    override val context: Context
        get() = this //To change initializer of created properties use File | Settings | File Templates.

    override fun snack(message: String) {
        snack(message, Snackbar.LENGTH_SHORT)
    }

    override fun snack(message: String, duration: Int) {
        val snackbar = Snackbar.make(parentContainer, message, duration)
        snackbar.setAction(R.string.ok, { snackbar.dismiss() })
        snackbar.show()
    }

    //TODO make a generic error utils which takes a type of error container
    // (eg. dialog, snackbar, toast), a CTA on okay click (eg. dismiss the dialog, snack etc)

}
