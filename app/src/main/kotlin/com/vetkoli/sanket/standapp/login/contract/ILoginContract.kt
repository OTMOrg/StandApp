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

        override fun hideProgress()

        fun goToHomeActivity()

    }

    interface LoginPresenter: IBaseContract.BasePresenter {

        fun validateInputAndSignIn(emailId: String, password: String)

    }

    interface SignupView : IBaseContract.BaseView {

        fun showEmailEmptyError()

        fun showPasswordEmptyError()

        fun showProgress()

        override fun hideProgress()

        fun goToHomeActivity()

    }

    interface SignupPresenter : IBaseContract.BasePresenter {

        fun validateInputAndSignUp(email: String, password: String)

    }

}