package com.vetkoli.sanket.standapp.base.contract

import android.content.Context

/**
 * Created by Sanket on 1/12/17.
 */

class IBaseContract {

    internal interface BaseView {

        val context: Context

        fun showError(message: String)

    }

    internal interface BasePresenter {

        fun subscribe()

        fun unSubscribe()

    }

}
