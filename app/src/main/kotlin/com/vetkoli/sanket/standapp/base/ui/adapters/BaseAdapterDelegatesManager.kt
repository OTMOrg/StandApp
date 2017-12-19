package com.vetkoli.sanket.standapp.base.ui.adapters

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

    fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)


}