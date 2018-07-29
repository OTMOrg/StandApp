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
    /*val url = "https://firebasestorage.googleapis.com/v0/b/stand-app-8a4e8.appspot.com/o/test_image.jpg?alt=media&token=3ec4d6c5-288a-4f0f-9895-e6f442ff10cf"
    val thumb = "https://firebasestorage.googleapis.com/v0/b/stand-app-8a4e8.appspot.com/o/Averaging_screenshot_27.02.2018_50.png?alt=media&token=257de6cf-295b-4f82-9634-f2b32c5f3e88"*/

    /*val requestBuilder = Glide.with(App.appContext).load(url)

    requestBuilder
            .thumbnail(Glide.with(App.appContext).load(thumb))
            .load(url)
            .into(this)*/

    /*Glide.with(App.appContext).load(thumb).apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(this)

    postDelayed(Runnable { Glide.with(App.appContext).load(url).apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(this) }, 2000)*/


}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)