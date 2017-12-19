package com.vetkoli.sanket.standapp.home.ui.adapters

import com.vetkoli.sanket.standapp.base.models.Item
import com.vetkoli.sanket.standapp.base.ui.adapters.BaseAdapter

/**
 * Created by Sanket on 16/12/17.
 */
class MembersAdapter(memberList: MutableList<Item>) : BaseAdapter<Item>() {

    init {
        delegationManager.addDelegate(MemberAdapterDelegate())
        setItems(memberList)
    }
}