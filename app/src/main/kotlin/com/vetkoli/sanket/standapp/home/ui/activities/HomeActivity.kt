package com.vetkoli.sanket.standapp.home.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity
import com.vetkoli.sanket.standapp.home.contract.IHomeContract
import com.vetkoli.sanket.standapp.home.presenter.HomePresenter
import com.vetkoli.sanket.standapp.home.ui.adapters.MembersAdapter
import com.vetkoli.sanket.standapp.models.Member
import com.vetkoli.sanket.standapp.utils.MockUtils
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

        init()
    }

    /*override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                android.R.id.home -> onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }*/


    /***
     * Helper
     */

    private fun init() {
//        initToolbar()
        initRecyclerView()
    }

    /*private fun initToolbar() {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }*/

    private fun initRecyclerView() {
        rvHome.layoutManager = LinearLayoutManager(this)
        var memberList = mutableListOf<Member>()
        memberList.addAll(MockUtils.getMockedMembers(10))
        val memberAdapter = MembersAdapter(memberList)
        rvHome.adapter = memberAdapter
    }

    /***
     * Contract
     */

    override fun snack(message: String, duration: Int, buttonString: String,
                       listener: View.OnClickListener?) {
        showSnack(message, duration, buttonString, listener, parentContainer)
    }
}
