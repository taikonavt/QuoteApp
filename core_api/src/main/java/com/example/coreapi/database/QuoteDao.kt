package com.example.coreapi.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coreapi.entities.QuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Query("SELECT * FROM QUOTE_TABLE WHERE quote_ticker = :ticker")
    fun getQuotes(ticker: String): Flow<List<QuoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuotes(list: List<QuoteEntity>)

    @Query("DELETE FROM QUOTE_TABLE" )
    suspend fun nukeTable()
}