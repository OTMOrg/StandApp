package com.vetkoli.sanket.standapp.home.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.vetkoli.sanket.standapp.base.models.Item
import com.vetkoli.sanket.standapp.base.ui.adapters.BaseAdapterDelegatesManager
import com.vetkoli.sanket.standapp.base.ui.viewholders.BaseViewHolder
import com.vetkoli.sanket.standapp.models.Member
import com.vetkoli.sanket.standapp.utils.unsafeLazy

/**
 * Created by Sanket on 16/12/17.
 */
class MembersAdapter(var memberList: MutableList<Member>) : RecyclerView.Adapter<BaseViewHolder>() {

    private val delegationManager: BaseAdapterDelegatesManager<Item>
            by unsafeLazy { BaseAdapterDelegatesManager<Item>() }

    init {
        delegationManager.addDelegate(MemberAdapterDelegate())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return delegationManager.onCreateViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        delegationManager.onBindViewHolder(holder, memberList.get(position))
    }


}