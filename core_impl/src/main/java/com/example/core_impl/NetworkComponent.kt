package com.example.core_impl

import com.example.coreapi.network.NetworkProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent : NetworkProvider
