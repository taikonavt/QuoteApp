package com.example.quoteapp

import android.app.Application
import com.example.coreapi.mediator.AppProvider
import com.example.coreapi.database.DatabaseProvider
import com.example.coreapi.mediator.ProvidersFacade
import com.example.corefactory.CoreProvidersFactory
import dagger.Component

@Component(
    dependencies = [AppProvider::class, DatabaseProvider::class],
    modules = [MediatorsBindings::class]
)
interface FacadeComponent : ProvidersFacade {

    companion object {

        fun init(application: Application): FacadeComponent =
            DaggerFacadeComponent.builder()
                .appProvider(AppComponent.create(application))
                .databaseProvider(CoreProvidersFactory.createDatabaseBuilder(AppComponent.create(application)))
                .build()
    }

    fun inject(app: App)
}