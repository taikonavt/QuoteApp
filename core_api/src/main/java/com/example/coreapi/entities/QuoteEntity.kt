package com.example.coreapi.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "quote_table")
data class QuoteEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val price: String?
)