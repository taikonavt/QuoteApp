package com.example.coreapi.database

interface DatabaseProvider {

    fun provideDatabase(): QuoteDatabaseContract

    fun getQuoteDao(): QuoteDao
}