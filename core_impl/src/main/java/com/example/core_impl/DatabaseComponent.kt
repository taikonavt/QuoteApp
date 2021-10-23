package com.example.core_impl

import com.example.coreapi.database.DatabaseProvider
import com.example.coreapi.mediator.AppProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class],
    modules = [DatabaseModule::class]
)
interface DatabaseComponent : DatabaseProvider