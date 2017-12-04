package com.vetkoli.sanket.standapp.base.contract

import android.content.Context
import android.view.View

/**
 * Created by Sanket on 1/12/17.
 */

class IBaseContract {

    internal interface BaseView {

        val context: Context

        fun snack(message: String)

        fun snack(message: String, duration: Int)

        fun snack(message: String, duration: Int, listener: View.OnClickListener)

        fun snack(message: String, duration: Int, buttonString: String)

        fun snack(message: String, duration: Int, buttonString: String, listener: View.OnClickListener)
    }

    internal interface BasePresenter {

        fun subscribe()

        fun unSubscribe()

    }

}
