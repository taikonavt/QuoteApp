package com.example.core_impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coreapi.database.QuoteDatabaseContract
import com.example.coreapi.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDatabase : RoomDatabase(), QuoteDatabaseContract