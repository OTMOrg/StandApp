package com.vetkoli.sanket.standapp.base.ui.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.vetkoli.sanket.standapp.base.contract.IBaseContract

/**
 * Created by Sanket on 30/11/17.
 */

abstract class BaseActivity : AppCompatActivity() , IBaseContract.BaseView {

    override val context: Context
        get() = this //To change initializer of created properties use File | Settings | File Templates.

}
