package com.vetkoli.sanket.standapp.base.contract;

import android.content.Context;

/**
 * Created by Sanket on 1/12/17.
 */

public class IBaseContract {

    interface BaseView {

        Context getContext();

        void showError(String message);

    }

    interface BasePresenter {

        void subscribe();

        void unSubscribe();

    }

}
