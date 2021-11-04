package com.example.quote_detail.di

import androidx.lifecycle.ViewModel
import com.example.quote_detail.QuoteDetailViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface QuoteDetailModule {

    companion object {
        @Provides
        @Singleton
        @JvmStatic
        fun provideQuoteDetailViewModel(
            map: @JvmSuppressWildcards MutableMap<Class<out ViewModel>, ViewModel>,
        ): ViewModel = QuoteDetailViewModel().also {
            map[QuoteDetailViewModel::class.java] = it
        }
    }
}
