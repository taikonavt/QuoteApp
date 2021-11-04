package com.example.core_impl

import com.example.coreapi.network.MoexApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.example.core_impl.json.DeserializeToString
import com.example.core_impl.json.SerializeNulls
import javax.inject.Singleton

private const val BASE_URL = "https://iss.moex.com/"
private const val TIMEOUT_IN_SEC = 30L

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(factory: Converter.Factory): Retrofit {
        return RetrofitFactory.create(BASE_URL, TIMEOUT_IN_SEC, factory)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(DeserializeToString.Factory)
            .add(SerializeNulls.Factory)
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): Converter.Factory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Reusable
    fun provideMoexApi(retrofit: Retrofit): MoexApi {
        return retrofit.create(MoexApi::class.java)
    }
}