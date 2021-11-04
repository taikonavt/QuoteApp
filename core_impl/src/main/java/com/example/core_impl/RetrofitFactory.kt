package com.example.core_impl

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


object RetrofitFactory {

    fun create(url : String,
               timeoutInSec : Long,
               converterFactory: Converter.Factory?,
               vararg interceptors: Interceptor
    ): Retrofit {


        val client = OkHttpClient.Builder()
            .apply {
                interceptors.forEach {
                    addInterceptor(it)
                }
            }
            .connectTimeout(timeoutInSec, TimeUnit.SECONDS)
            .readTimeout(timeoutInSec, TimeUnit.SECONDS)
            .writeTimeout(timeoutInSec, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .apply {
                converterFactory?.let {
                    addConverterFactory(converterFactory)
                }
            }
            .build()
    }
}
