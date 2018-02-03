package com.vetkoli.sanket.standapp.home.contract

import com.vetkoli.sanket.standapp.base.contract.IBaseContract

/**
 * Created by Sanket on 16/12/17.
 */
interface IHomeContract {

    interface View : IBaseContract.BaseView {
        override fun showProgress(message: String)

        override fun hideProgress()
    }

    interface Presenter : IBaseContract.BasePresenter {

    }

}