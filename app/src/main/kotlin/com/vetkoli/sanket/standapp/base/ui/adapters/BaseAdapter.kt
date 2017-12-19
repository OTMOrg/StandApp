package com.vetkoli.sanket.standapp.base.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.vetkoli.sanket.standapp.base.ui.viewholders.BaseViewHolder
import com.vetkoli.sanket.standapp.utils.unsafeLazy

/**
 * Created by Sanket on 19/12/17.
 */
abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder>() {

    private lateinit var itemList: MutableList<T>

    protected val delegationManager: BaseAdapterDelegatesManager<T>
            by unsafeLazy { BaseAdapterDelegatesManager<T>() }

    protected fun setItems(itemList: MutableList<T>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return delegationManager.onCreateViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        delegationManager.onBindViewHolder(holder, itemList.get(position))
    }


}