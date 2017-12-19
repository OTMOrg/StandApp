package com.vetkoli.sanket.standapp.home.ui.adapters

import android.view.View
import com.vetkoli.sanket.standapp.base.models.Item
import com.vetkoli.sanket.standapp.base.ui.viewholders.BaseViewHolder
import com.vetkoli.sanket.standapp.models.Member
import com.vetkoli.sanket.standapp.utils.load
import com.vetkoli.sanket.standapp.utils.toDateString
import kotlinx.android.synthetic.main.item_home.view.*

/**
 * Created by Sanket on 19/12/17.
 */
class MemberViewHolder(itemView: View) : BaseViewHolder(itemView) {

    override fun bind(item: Item) {
        val member = item as Member

        itemView.ivProfilePic.load(member.profilePic)
        itemView.tvName.setText(member.name)
        itemView.tvDate.setText(member.lastUpdatedOn.toDateString())
        itemView.tvMissed.setText(member.missList!!.size.toString())
    }


}