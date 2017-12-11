package com.vetkoli.sanket.standapp.utils

import android.app.Activity
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.view.View

/**
 * Created by Sanket on 9/12/17.
 */
//https://stackoverflow.com/a/44124782/4291698
class ViewExtensions<T> {

    inline fun <reified T : View> View.find(id: Int): T = findViewById(id) as T
    inline fun <reified T : View> Activity.find(id: Int): T = findViewById(id) as T
    inline fun <reified T : View> Fragment.find(id: Int): T = view?.findViewById(id) as T


    //https://medium.com/@quiro91/improving-findviewbyid-with-kotlin-4cf2f8f779bb
    fun <T : View> Activity.bind(@IdRes idRes: Int): Lazy<T> {
        @Suppress("UNCHECKED_CAST")
        return unsafeLazy { findViewById(idRes) as T }
    }

    fun <T : View> View.bind(@IdRes idRes: Int): Lazy<T> {
        @Suppress("UNCHECKED_CAST")
        return unsafeLazy { findViewById(idRes) as T }
    }

    public fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)
}