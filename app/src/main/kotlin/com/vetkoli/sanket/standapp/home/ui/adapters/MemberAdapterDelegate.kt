package com.vetkoli.sanket.standapp.home.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.models.Item
import com.vetkoli.sanket.standapp.base.ui.adapters.BaseAdapterDelegate
import com.vetkoli.sanket.standapp.base.ui.viewholders.BaseViewHolder
import com.vetkoli.sanket.standapp.models.Member
import com.vetkoli.sanket.standapp.utils.inflate

/**
 * Created by Sanket on 16/12/17.
 */
class MemberAdapterDelegate: BaseAdapterDelegate<Item> {
    override fun isForViewType(item: Item): Boolean {
        return item is Member
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return MemberViewHolder(parent.inflate(R.layout.item_home))
    }

    override fun onBindViewHolder(item: Item, holder: BaseViewHolder) {
        holder.bind(item)
    }

    override fun getItemViewType(): Int {
        return Item.MEMBER
    }
}