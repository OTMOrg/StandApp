package com.vetkoli.sanket.standapp.home.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.application.Constants
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity
import com.vetkoli.sanket.standapp.home.contract.IHomeContract
import com.vetkoli.sanket.standapp.home.presenter.HomePresenter
import com.vetkoli.sanket.standapp.home.ui.adapters.MembersAdapter
import com.vetkoli.sanket.standapp.models.Member
import com.vetkoli.sanket.standapp.splash.ui.activities.SplashActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(), IHomeContract.View {

    private val presenter: IHomeContract.Presenter by unsafeLazy { HomePresenter(this) }

    private lateinit var memberList: MutableList<Member>

    private lateinit var memberAdapter: MembersAdapter

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                android.R.id.home -> {
                    onBackPressed()
                    return true
                }
                R.id.action_logout -> logout()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        startActivity(SplashActivity.newIntent(this))
        finish()
    }


    /***
     * Helper
     */

    private fun init() {
        initToolbar()
        initRecyclerView()
        initData()
    }

    private fun initData() {
        showProgress(getString(R.string.loading))
        var database = FirebaseDatabase.getInstance()
        val databaseReference = database.getReference()
        databaseReference
                .child(Constants.TEAMS)
                .child(Constants.FARMER_APP)
                .child(Constants.TEAM_NAME)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot?) {
                        hideProgress()
                        val value : String = snapshot!!.value as String
                        val actionBar = supportActionBar
                        actionBar?.subtitle = value
                    }

                    override fun onCancelled(error: DatabaseError?) {
                        hideProgress()
                        Log.e(localClassName, "Failed to read team name")
                    }
                })

        /*val uid = FirebaseAuth.getInstance().currentUser?.uid

        val member = Member()
        val currentTime = System.currentTimeMillis()
        val list = mutableListOf<Long>()
        list.add(currentTime)
        member.apply {
            id = uid.toString()
            name = "Vishal Pamnani"
            missList = list
            lastUpdatedBy = ""
            lastUpdatedOn = currentTime
            profilePic = ""
        }

        databaseReference.child(Constants.TEAMS)
                .child(Constants.FARMER_APP)
                .child(Constants.MEMBERS)
                .child(uid)
                .setValue(member)*/

        databaseReference.child(Constants.TEAMS)
                .child(Constants.FARMER_APP)
                .child(Constants.MEMBERS).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                dataSnapshot?.children?.forEach{childSnapshot ->
                    val member: Member? = childSnapshot.getValue(Member::class.java)
                    member?.let { memberList.add(it) }
                }
                memberAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(p0: DatabaseError?) {
                Log.e(localClassName, "Failed to read memebrs")
            }
        })

    }

    private fun initToolbar() {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initRecyclerView() {
        rvHome.layoutManager = LinearLayoutManager(this)
        memberList = mutableListOf<Member>()
        memberAdapter = MembersAdapter(memberList)
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
