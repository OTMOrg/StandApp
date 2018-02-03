package com.vetkoli.sanket.standapp.base.contract

import android.content.Context
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.application.App

/**
 * Created by Sanket on 1/12/17.
 */

interface IBaseContract {

    interface BaseView {

        val context: Context

        fun toast(message: String, duration: Int = Toast.LENGTH_SHORT)

        fun snack(message: String, duration: Int = Snackbar.LENGTH_SHORT,
                  buttonString: String = App.appContext.getString(R.string.ok), listener: View.OnClickListener? = null)

        fun showProgress(message: String)

        fun hideProgress()
    }

    interface BasePresenter {

        fun subscribe()

        fun unSubscribe()

    }

}
