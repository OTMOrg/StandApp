package com.vetkoli.sanket.standapp.home.ui.adapters

import android.view.View
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.application.App
import com.vetkoli.sanket.standapp.base.models.Item
import com.vetkoli.sanket.standapp.base.ui.viewholders.BaseViewHolder
import com.vetkoli.sanket.standapp.home.ui.activities.HomeActivity
import com.vetkoli.sanket.standapp.models.Member
import com.vetkoli.sanket.standapp.utils.load
import com.vetkoli.sanket.standapp.utils.toDateString
import kotlinx.android.synthetic.main.item_member.view.*
import java.util.*

/**
 * Created by Sanket on 19/12/17.
 */
class MemberViewHolder(itemView: View) : BaseViewHolder(itemView) {

    override fun bind(item: Item) {
        val member = item as Member

        itemView.ivProfilePic.load(member.profilePic)
        itemView.tvName.text = member.name
        itemView.tvDate.text = member.lastUpdatedOn.toDateString()
        itemView.tvMissCount.text = App.appContext.getString(R.string.d_missed_this_month, getMissCount(member))
        itemView.btnPlusOne.setOnClickListener {
            (itemView.context as HomeActivity).showConfirmationDialogToPlusOne(adapterPosition)
        }
    }

    private fun getMissCount(member: Member): Int {
        val instance = Calendar.getInstance()
        instance.timeInMillis = System.currentTimeMillis()
        val missMap = member.missMap
        val year = "Id_" + instance.get(Calendar.YEAR)
        val month = "Id_" + instance.get(Calendar.MONTH)
        if (!missMap.containsKey(year)) {
            return 0
        } else {
            val monthMap = missMap[year]
            if (monthMap == null || !monthMap.containsKey(month)) {
                return 0
            } else {
                return monthMap.size
            }
        }
    }


}