package com.example.coreapi.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.coreapi.entities.QuoteEntity

@Dao
interface QuoteDao {

    @Query("SELECT * FROM QUOTE_TABLE")
    suspend fun getQuotes(): List<QuoteEntity>

    @Insert
    suspend fun createQuotes(list: List<QuoteEntity>)
}