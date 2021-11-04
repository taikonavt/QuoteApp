package com.example.quote_detail.di

import com.example.coreapi.mediator.ProvidersFacade
import com.example.coreapi.network.NetworkProvider
import com.example.coreapi.viewmodel.ViewModelsProvider
import com.example.corefactory.CoreProvidersFactory
import com.example.quote_detail.QuoteDetailFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [QuoteDetailModule::class],
    dependencies = [ProvidersFacade::class, ViewModelsProvider::class, NetworkProvider::class]
)
interface QuoteDetailComponent : ViewModelsProvider {

    companion object {

        fun create(providersFacade: ProvidersFacade) : QuoteDetailComponent
        {
            return DaggerQuoteDetailComponent
                .builder()
                .viewModelsProvider(CoreProvidersFactory.createViewModelBuilder())
                .providersFacade(providersFacade)
                .networkProvider(CoreProvidersFactory.createNetworkBuilder())
                .build()
        }
    }

    fun inject(fragment: QuoteDetailFragment)
}