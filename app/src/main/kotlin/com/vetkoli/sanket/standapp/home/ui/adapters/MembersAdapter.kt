package com.vetkoli.sanket.standapp.home.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.models.Member
import com.vetkoli.sanket.standapp.utils.inflate

/**
 * Created by Sanket on 16/12/17.
 */
class MembersAdapter(var memberList: MutableList<Member>) : RecyclerView.Adapter<MemberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MemberViewHolder {
        return MemberViewHolder(parent!!.inflate(R.layout.item_member))
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun onBindViewHolder(holder: MemberViewHolder?, position: Int) {
        holder?.bind(memberList[position])
    }

}