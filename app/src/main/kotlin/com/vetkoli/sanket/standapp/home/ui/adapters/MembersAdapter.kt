package com.vetkoli.sanket.standapp.home.ui.adapters

import android.support.v7.util.SortedList
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.util.SortedListAdapterCallback
import android.view.ViewGroup
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.models.Member
import com.vetkoli.sanket.standapp.utils.inflate
import com.vetkoli.sanket.standapp.utils.unsafeLazy

/**
 * Created by Sanket on 16/12/17.
 */
class MembersAdapter() : RecyclerView.Adapter<MemberViewHolder>() {

    private val memberList by unsafeLazy { SortedList(Member::class.java,object : SortedListAdapterCallback<Member>(this) {
        override fun compare(o1: Member, o2: Member): Int = o2.missCount - o1.missCount;

        override fun areContentsTheSame(oldItem: Member, newItem: Member): Boolean = oldItem.id == newItem.id

        override fun areItemsTheSame(item1: Member, item2: Member): Boolean = item1 == item2
    }) }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MemberViewHolder {
        return MemberViewHolder(parent!!.inflate(R.layout.item_member))
    }

    override fun getItemCount(): Int {
        return memberList.size()
    }

    override fun onBindViewHolder(holder: MemberViewHolder?, position: Int) {
        holder?.bind(memberList[position])
    }

    fun clear() {
        memberList.clear()
    }

    fun add(member: Member) {
        memberList.add(member)
    }

    fun get(position: Int): Member {
        return memberList[position]
    }

}