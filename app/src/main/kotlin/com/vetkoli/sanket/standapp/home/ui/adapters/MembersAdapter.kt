package com.vetkoli.sanket.standapp.home.ui.adapters

import com.vetkoli.sanket.standapp.base.ui.adapters.BaseAdapter
import com.vetkoli.sanket.standapp.models.Member

/**
 * Created by Sanket on 16/12/17.
 */
class MembersAdapter(memberList: MutableList<Member>) : BaseAdapter<Member>() {

    init {
        delegationManager.addDelegate(MemberAdapterDelegate())
        setItems(memberList)
    }
}