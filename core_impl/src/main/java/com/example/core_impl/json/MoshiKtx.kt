package com.example.core_impl.json

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

inline fun <reified T: Any> T.toJson(): String {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build().adapter(T::class.java).toJson(this)
}

inline fun <reified T: Any> String.fromJson() : T? {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build().adapter(T::class.java).fromJson(this)
}
