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
        for (delegate in delegates) {
            if (delegate.isForViewType(item)) {
                return delegate.getItemViewType()
            }
        }
        throw UnsupportedOperationException()
    }

    public fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        for (delegate in delegates) {
            if (viewType == delegate.getItemViewType()) {
                return delegate.onCreateViewHolder(parent)
            }
        }
        throw UnsupportedOperationException()
    }

    public fun onBindViewHolder(holder: BaseViewHolder, item: T) {
        for (delegate in delegates) {
            if (delegate.isForViewType(item)) {
                delegate.onBindViewHolder(holder, item)
            }
        }
    }

    fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)


}