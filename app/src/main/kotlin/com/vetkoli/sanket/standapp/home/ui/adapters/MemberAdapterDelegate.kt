package com.vetkoli.sanket.standapp.home.ui.adapters

import android.content.Context
import android.view.ViewGroup
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.base.ui.adapters.BaseAdapterDelegate
import com.vetkoli.sanket.standapp.base.ui.viewholders.BaseViewHolder
import com.vetkoli.sanket.standapp.home.ui.activities.MemberDetailActivity
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
        val view = parent.inflate(R.layout.item_member)
        view.setOnClickListener{ goToMemberDetail(parent.context) }
        return MemberViewHolder(view)
    }

    private fun goToMemberDetail(context: Context?) {
        context?.startActivity(MemberDetailActivity.newIntent(context))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: Member) {
        holder.bind(item)
    }

    override fun getItemViewType(): Int {
        return 0
    }
}