package com.vetkoli.sanket.standapp.home.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity
import com.vetkoli.sanket.standapp.home.contract.IHomeContract
import com.vetkoli.sanket.standapp.home.presenter.HomePresenter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(), IHomeContract.View {

    private val presenter: IHomeContract.Presenter by unsafeLazy { HomePresenter(this) }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            return intent
        }
    }

    /***
     * Lifecycle
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    /***
     * Contract
     */

    override fun snack(message: String, duration: Int, buttonString: String,
                       listener: View.OnClickListener?) {
        showSnack(message, duration, buttonString, listener, parentContainer)
    }
}
