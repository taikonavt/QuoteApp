package com.example.corefactory

import com.example.core_impl.DaggerDatabaseComponent
import com.example.core_impl.DaggerViewModelComponent
import com.example.coreapi.database.DatabaseProvider
import com.example.coreapi.mediator.AppProvider
import com.example.coreapi.viewmodel.ViewModelsProvider

object CoreProvidersFactory {

    fun createDatabaseBuilder(appProvider: AppProvider): DatabaseProvider {
        return DaggerDatabaseComponent.builder().appProvider(appProvider).build()
    }

    fun createViewModelBuilder(): ViewModelsProvider {
        return DaggerViewModelComponent.create()
    }
}