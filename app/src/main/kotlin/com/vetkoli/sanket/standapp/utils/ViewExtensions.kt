package com.vetkoli.sanket.standapp.utils

import android.app.Activity
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vetkoli.sanket.standapp.R
import com.vetkoli.sanket.standapp.application.App

/**
 * Created by Sanket on 9/12/17.
 */
//https://stackoverflow.com/a/44124782/4291698

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

fun ImageView.load(url: String?) {
    Glide.with(App.appContext).load(url).apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher).fallback(R.mipmap.ic_launcher)).into(this)
}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)