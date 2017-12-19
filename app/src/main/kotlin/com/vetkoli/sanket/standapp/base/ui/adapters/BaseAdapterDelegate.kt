package com.vetkoli.sanket.standapp.base.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.vetkoli.sanket.standapp.base.ui.viewholders.BaseViewHolder

/**
 * Created by Sanket on 16/12/17.
 */
/**
 * @param <T> the type of adapters data i.e. Item
</Accessory></T> */
interface BaseAdapterDelegate<T> {

    /**
     * Called to determine whether this AdapterDelegate is the responsible for the given data
     * element.
     *
     * @param item The data of the Adapter
     * @return true, if this item is responsible, otherwise false
     */
    fun isForViewType(item: T): Boolean

    /**
     * Creates the  [RecyclerView.ViewHolder] for the given data source item
     *
     * @param parent The ViewGroup parent of the given data
     * @return The new instantiated [RecyclerView.ViewHolder]
     */
    fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder

    /**
     * Called to bind the [RecyclerView.ViewHolder] to the item of the datas source set
     *
     * @param item The data
     * @param holder The [RecyclerView.ViewHolder] to bind
     */
    fun onBindViewHolder(holder: BaseViewHolder, item: T)

    fun getItemViewType(): Int
}