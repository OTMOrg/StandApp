package com.vetkoli.sanket.standapp.home.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity
import com.vetkoli.sanket.standapp.home.contract.IMemberDetailContract
import com.vetkoli.sanket.standapp.home.presenter.MemberDetailPresenter
import kotlinx.android.synthetic.main.activity_member_detail.*

class MemberDetailActivity : BaseActivity(), IMemberDetailContract.View {

    private val presenter: IMemberDetailContract.Presenter by unsafeLazy { MemberDetailPresenter(this) }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, MemberDetailActivity::class.java)
            return intent
        }
    }

    /***
     * Lifecycle
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_detail)
    }

    /***
     * Helper
     */



    /***
     * Contract
     */

    override fun snack(message: String, duration: Int, buttonString: String, listener: View.OnClickListener?) {
        showSnack(message, duration, buttonString, listener, parentContainer)
    }
}
