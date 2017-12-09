package com.vetkoli.sanket.standapp.login.contract

import com.vetkoli.sanket.standapp.base.contract.IBaseContract

/**
 * Created by Sanket on 9/12/17.
 */
interface ILoginContract {

    interface LoginView: IBaseContract.BaseView {

        fun showEmailEmptyError()

        fun showPasswordEmptyError()

        fun showProgress()

        fun hideProgress()

    }

    interface LoginPresenter: IBaseContract.BasePresenter {

        fun validateInput(emailId: String, password: String)

    }
}