package com.vetkoli.sanket.standapp.home.ui.activities

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.application.App
import com.vetkoli.sanket.standapp.application.Constants
import com.vetkoli.sanket.standapp.base.ui.activities.BaseActivity
import com.vetkoli.sanket.standapp.home.contract.IHomeContract
import com.vetkoli.sanket.standapp.home.presenter.HomePresenter
import com.vetkoli.sanket.standapp.home.ui.adapters.MembersAdapter
import com.vetkoli.sanket.standapp.models.Member
import com.vetkoli.sanket.standapp.models.UpdatedByMetadata
import com.vetkoli.sanket.standapp.splash.ui.activities.SplashActivity
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class HomeActivity : BaseActivity(), IHomeContract.View {

    private val presenter: IHomeContract.Presenter by unsafeLazy { HomePresenter(this) }

    private lateinit var memberList: MutableList<Member>

    private lateinit var memberAdapter: MembersAdapter

    private lateinit var currentMember: Member

    private var teamName: String = ""

    private var teamKey: String = ""

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
        initRecyclerView()
        initData()
    }

    private fun initData() {
        showProgress(getString(R.string.loading))
        val database = FirebaseDatabase.getInstance()
        val databaseReference = database.getReference()
        getData(databaseReference)

//        addDBEntry(databaseReference)

    }

    private fun addDBEntry(databaseReference: DatabaseReference) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid

        val member = Member()
        val currentTime = System.currentTimeMillis()
        member.apply {
            id = uid.toString()
            name = "Neha Bisht"
            missMap = mutableMapOf()
            lastUpdatedBy = ""
            lastUpdatedOn = currentTime
            profilePic = ""
        }

        databaseReference.child(Constants.TEAMS)
                .child("creditApp")
                .child(Constants.MEMBERS)
                .child(uid)
                .setValue(member)
    }

    private fun getData(databaseReference: DatabaseReference) {
        databaseReference.child(Constants.TEAMS)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot?) {
                        if (dataSnapshot != null) {
                            val userId = FirebaseAuth.getInstance().currentUser?.uid
                            for (childSnapshot in dataSnapshot.children) {
                                val membersSnapshot = childSnapshot.child(Constants.MEMBERS)
                                for (memberSnapshot in membersSnapshot.children) {
                                    val member = memberSnapshot.getValue(Member::class.java)
                                    if (member != null) {
                                        if (userId.equals(member.id)) {
                                            teamKey = childSnapshot.key
                                            teamName = childSnapshot.child(Constants.TEAM_NAME).value as String
                                            initView(member, databaseReference)
                                            break
                                        }
                                    }
                                }
                            }
                        }
                    }

                    override fun onCancelled(p0: DatabaseError?) {
                        hideProgress()
                        Log.e(localClassName, "Error while getting team name")
                    }
                })
    }

    private fun initView(member: Member, databaseReference: DatabaseReference) {
        setSubTitle(teamName)
        currentMember = member
        initTeamView(databaseReference)
    }

    private fun initTeamView(databaseReference: DatabaseReference) {
        if (!TextUtils.isEmpty(teamKey)) {
            databaseReference.child(Constants.TEAMS)
                    .child(teamKey)
                    .child(Constants.MEMBERS).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot?) {
                    memberList.clear()
                    dataSnapshot?.children?.forEach { childSnapshot ->
                        val member: Member? = childSnapshot.getValue(Member::class.java)
                        member?.let {
                            memberList.add(it)
                            if (it.id.equals(FirebaseAuth.getInstance().currentUser!!.uid)) {
                                currentMember = it
                            }
                        }
                    }
                    memberAdapter.notifyDataSetChanged()
                    hideProgress()
                }

                override fun onCancelled(p0: DatabaseError?) {
                    hideProgress()
                    Log.e(localClassName, "Failed to read memebrs")
                }
            })
        } else {
            snack("Team corresponding to the current user not found")
            hideProgress()
        }
    }

    private fun setSubTitle(title: String) {
        val actionBar = supportActionBar
        actionBar?.subtitle = title
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

    /**
     * Helper
     */

    fun showConfirmationDialogToPlusOne(position: Int) {
        val member = memberList.get(position)
        val dialogBuilder = AlertDialog.Builder(this).apply {
            setTitle("Confirmation")
            setMessage(getString(R.string.plus_one_confirmation_message, member.name))
            setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                plusOneMemberIfNotAlreadyDoneForToday(position)
                dialog.dismiss()
             })
            setNegativeButton("No", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
        }
        val dialog = dialogBuilder.create()
        dialog.show()
        val buttonNegative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
        val buttonPositive = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
        buttonNegative.setTextColor(resources.getColor(R.color.colorPrimary))
        buttonPositive.setTextColor(resources.getColor(R.color.colorPrimary))
    }

    private fun plusOneMemberIfNotAlreadyDoneForToday(position: Int) {
        val member = memberList[position]
        if (!isMemberUpdatedToday(member)) {
            val timeMillis = System.currentTimeMillis()
            member.missCount++
            member.lastUpdatedOn = timeMillis

            val instance = Calendar.getInstance()
            instance.timeInMillis = timeMillis

            val updatedByMetadata = UpdatedByMetadata()
            updatedByMetadata.updatedAt = timeMillis
            updatedByMetadata.updatedById = currentMember.id
            updatedByMetadata.updatedByName = currentMember.name!!

            val missMap = member.missMap
            val year = "Id_" + instance.get(Calendar.YEAR)
            val month = "Id_" + instance.get(Calendar.MONTH)
            val dayOfMonth = "Id_" + instance.get(Calendar.DAY_OF_MONTH)
            if (missMap.containsKey(year)) {
                val missMonthMap = missMap[year]
                if (missMonthMap != null) {
                    if (missMonthMap.containsKey(month)) {
                        val missDayMap = missMonthMap[month]
                        if (missDayMap != null) {
                            missDayMap.put(dayOfMonth, updatedByMetadata)
                        } else {
                            initAndPutMissDayIntoMonth(dayOfMonth, updatedByMetadata, missMonthMap, month)
                        }
                    } else {
                        initAndPutMissDayIntoMonth(dayOfMonth, updatedByMetadata, missMonthMap, month)
                    }
                } else {
                    initAndPutMissMonthIntoYear(dayOfMonth, updatedByMetadata, month, missMap, year)
                }
            } else {
                initAndPutMissMonthIntoYear(dayOfMonth, updatedByMetadata, month, missMap, year)
            }

            val firebaseDatabase = FirebaseDatabase.getInstance()
            val databaseReference = firebaseDatabase.getReference("/teams/" + teamKey + "/members").child(member.id)
            databaseReference.setValue(member)
            databaseReference.child("lastUpdatedOn").setValue(timeMillis)
        } else {
            snack("Already updated today")
        }
    }

    private fun initAndPutMissMonthIntoYear(dayOfMonth: String, updatedByMetadata: UpdatedByMetadata,
                                            month: String, missMap: MutableMap<String, MutableMap<String, MutableMap<String, UpdatedByMetadata>>>, year: String) {
        val missMonthMap = mutableMapOf<String, MutableMap<String, UpdatedByMetadata>>()
        initAndPutMissDayIntoMonth(dayOfMonth, updatedByMetadata, missMonthMap, month)
        missMap.put(year, missMonthMap)
    }

    private fun initAndPutMissDayIntoMonth(dayOfMonth: String, updatedByMetadata: UpdatedByMetadata,
                                           missMonthMap: MutableMap<String, MutableMap<String, UpdatedByMetadata>>, month: String) {
        val missDayMap = mutableMapOf<String, UpdatedByMetadata>()
        missDayMap.put(dayOfMonth, updatedByMetadata)
        missMonthMap.put(month, missDayMap)
    }

    private fun isMemberUpdatedToday(member: Member): Boolean {
        val calendar = Calendar.getInstance()
        val memberLastUpdated = Calendar.getInstance()
        memberLastUpdated.timeInMillis = member.lastUpdatedOn
        if (memberLastUpdated.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
            return memberLastUpdated.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)
        }
        return false;
    }

    fun goToMemberDetail(position: Int) {
        val member = memberList.get(position)
        App.missMap = member.missMap
        startActivity(MemberDetailActivity.newIntent(context, member))
    }
}
