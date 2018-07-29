package com.vetkoli.sanket.standapp.home.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.application.App
import com.vetkoli.sanket.standapp.application.Constants
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity
import com.vetkoli.sanket.standapp.home.contract.IMemberDetailContract
import com.vetkoli.sanket.standapp.home.presenter.MemberDetailPresenter
import com.vetkoli.sanket.standapp.models.Member
import com.vetkoli.sanket.standapp.utils.load
import kotlinx.android.synthetic.main.activity_member_detail.*

class MemberDetailActivity : BaseActivity(), IMemberDetailContract.View {

    private val presenter: IMemberDetailContract.Presenter by unsafeLazy { MemberDetailPresenter(this) }
    private val member: Member by unsafeLazy { intent.getParcelableExtra(Constants.MEMBER) as Member }

    companion object {
        fun newIntent(context: Context, member: Member): Intent {
            val intent = Intent(context, MemberDetailActivity::class.java)
            intent.putExtra(Constants.MEMBER, member)
            return intent
        }
    }

    /***
     * Lifecycle
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_detail)
        init()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                android.R.id.home -> {
                    onBackPressed()
                    return true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }



    /***
     * Helper
     */

    private fun init() {
//        initIntent()
        initToolbar()
        initViews()
    }

    private fun initToolbar() {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initViews() {
        member.missMap = App.missMap
        ivProfilePic.load(member.profilePic)
        tvName.text = member.name
        tvMissCountSummary.text = getString(R.string.d_missed, member.missCount)

    }

    /*private fun initIntent() {
        val member: Member = intent.getParcelableExtra(Constants.MEMBER)
    }*/

    /***
     * Contract
     */

    override fun snack(message: String, duration: Int, buttonString: String, listener: View.OnClickListener?) {
        showSnack(message, duration, buttonString, listener, parentContainer)
    }
}
