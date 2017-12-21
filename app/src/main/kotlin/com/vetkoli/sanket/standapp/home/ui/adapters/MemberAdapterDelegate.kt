package com.vetkoli.sanket.standapp.home.ui.adapters

import android.view.ViewGroup
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.ui.adapters.BaseAdapterDelegate
import com.vetkoli.sanket.standapp.base.ui.viewholders.BaseViewHolder
import com.vetkoli.sanket.standapp.models.Member
import com.vetkoli.sanket.standapp.utils.inflate

/**
 * Created by Sanket on 16/12/17.
 */
class MemberAdapterDelegate: BaseAdapterDelegate<Member> {
    override fun isForViewType(item: Member): Boolean {
        return item is Member
    }

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder {
        return MemberViewHolder(parent.inflate(R.layout.item_home))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: Member) {
        holder.bind(item)
    }

    override fun getItemViewType(): Int {
        return 0
    }
}