package com.zeph7.newsapp.feature_news.util

import android.net.Uri
import com.google.gson.Gson

fun <T: Any> T.encodeToString(): String {
    return Uri.encode(Gson().toJson(this))
}

inline fun <reified T: Any> String.decodeFromString(): T {
    return Gson().fromJson(Uri.decode(this), T::class.java)
}