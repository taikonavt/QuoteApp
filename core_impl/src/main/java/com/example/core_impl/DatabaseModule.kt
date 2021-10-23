package com.example.core_impl

import android.content.Context
import androidx.room.Room
import com.example.coreapi.database.QuoteDao
import com.example.coreapi.database.QuoteDatabaseContract
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

private const val QUOTE_DATABASE_NAME = "QUOTE_DB"

@Module
class DatabaseModule {

    @Provides
    @Reusable
    fun provideQuoteDao(quoteDatabaseContract: QuoteDatabaseContract): QuoteDao {
        return quoteDatabaseContract.getQuoteDao()
    }

    @Provides
    @Singleton
    fun provideQuoteDatabase(context: Context): QuoteDatabaseContract {
        return Room.databaseBuilder(
            context,
            QuoteDatabase::class.java, QUOTE_DATABASE_NAME
        ).build()
    }
}