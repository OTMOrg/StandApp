package com.vetkoli.sanket.standapp.splash.contract

import com.vetkoli.sanket.standapp.base.contract.IBaseContract

/**
 * Created by Sanket on 4/12/17.
 */

interface ISplashContract {

    interface SplashView : IBaseContract.BaseView {

        fun goToLoginActivity()

        fun goToHomeActivity()

    }

    interface SplashPresenter : IBaseContract.BasePresenter {

        fun delegateFlow()

    }


}
