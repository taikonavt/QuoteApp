package com.example.coreapi.mediator

import android.content.Context

interface AppProvider {

    fun provideContext(): Context
}