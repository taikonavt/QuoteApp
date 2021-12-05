package com.example.quote_detail.di

import androidx.lifecycle.ViewModel
import com.example.base.BaseStateManager
import com.example.base.BaseViewModel
import com.example.coreapi.database.QuoteDao
import com.example.coreapi.network.MoexApi
import com.example.quote_detail.QuoteDetailStateManager
import com.example.quote_detail.QuoteDetailViewModel
import com.example.quote_detail.data.QuoteDetailRepository
import com.example.quote_detail.domain.QuoteDetailInteractor
import com.example.quote_detail.model.QuoteDetailRouteEvent
import com.example.quote_detail.model.QuoteDetailUIEvent
import com.example.quote_detail.model.QuoteDetailUIState
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface QuoteDetailModule {

    companion object {

        @Provides
        @Singleton
        @JvmStatic
        fun provideQuoteDetailStateManager() = QuoteDetailStateManager()

        @Provides
        @Singleton
        @JvmStatic
        fun provideQuoteDetailRepository(api: MoexApi, dao: QuoteDao) = QuoteDetailRepository(api, dao)

        @Provides
        @Singleton
        @JvmStatic
        fun provideQuoteDetailInteractor(repository: QuoteDetailRepository) = QuoteDetailInteractor(repository)

        @Provides
        @Singleton
        @JvmStatic
        fun provideQuoteDetailViewModel(
            stateManager: QuoteDetailStateManager,
            interactor: QuoteDetailInteractor
        ) : BaseViewModel<QuoteDetailUIEvent, QuoteDetailUIState, QuoteDetailRouteEvent, *> = QuoteDetailViewModel(stateManager, interactor)

//        @Provides
//        @Singleton
//        @JvmStatic
//        fun provideQuoteDetailViewModel(
//            map: @JvmSuppressWildcards MutableMap<Class<out ViewModel>, ViewModel>,
//            stateManager: QuoteDetailStateManager,
//            interactor: QuoteDetailInteractor
//        ): ViewModel = QuoteDetailViewModel(stateManager, interactor).also {
//            map[QuoteDetailViewModel::class.java] = it
//        }


    }
}
