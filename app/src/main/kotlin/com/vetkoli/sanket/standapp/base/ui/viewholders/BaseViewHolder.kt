package com.vetkoli.sanket.standapp.base.ui.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.vetkoli.sanket.standapp.base.models.Item

/**
 * Created by Sanket on 19/12/17.
 */
abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: Item)

}