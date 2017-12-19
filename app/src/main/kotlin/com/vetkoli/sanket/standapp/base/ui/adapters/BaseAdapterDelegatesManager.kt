package com.vetkoli.sanket.standapp.base.ui.adapters

import android.view.ViewGroup
import com.vetkoli.sanket.standapp.base.ui.viewholders.BaseViewHolder

/**
 * Created by Sanket on 16/12/17.
 */
class BaseAdapterDelegatesManager<T> {

    val delegates: MutableList<BaseAdapterDelegate<T>> by unsafeLazy  { mutableListOf<BaseAdapterDelegate<T>>() }

    fun addDelegate(delegate: BaseAdapterDelegate<T>): BaseAdapterDelegatesManager<T> {
        delegates.add(delegate)
        return this
    }

    public fun getItemViewType(item: T): Int {
        return getDelegate(item).getItemViewType()
    }

    public fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return getDelegate(viewType).onCreateViewHolder(parent)
    }

    public fun onBindViewHolder(holder: BaseViewHolder, item: T) {
        getDelegate(item).onBindViewHolder(holder, item)
    }

    private fun getDelegate(viewType: Int): BaseAdapterDelegate<T> {
        for (delegate in delegates) {
            if (viewType == delegate.getItemViewType()) {
                return delegate
            }
        }
        throw UnsupportedOperationException()
    }

    private fun getDelegate(item: T): BaseAdapterDelegate<T> {
        for (delegate in delegates) {
            if (delegate.isForViewType(item)) {
                return delegate
            }
        }
        throw UnsupportedOperationException()
    }

    fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)


}