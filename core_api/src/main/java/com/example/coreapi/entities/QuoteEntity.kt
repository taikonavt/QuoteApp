package com.example.coreapi.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val QUOTE_TABLE = "quote_table"
const val QUOTE_ID = "quote_id"
const val QUOTE_TICKER = "quote_ticker"
const val QUOTE_NAME = "quote_name"
const val QUOTE_OPEN = "quote_open"
const val QUOTE_LAST = "quote_last"
const val QUOTE_HIGH = "quote_high"
const val QUOTE_LOW = "quote_low"

@Entity(tableName = QUOTE_TABLE)
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = QUOTE_ID) val id: Long = 0,
    @ColumnInfo(name = QUOTE_TICKER) val ticker: String,
    @ColumnInfo(name = QUOTE_NAME) val name: String,
    @ColumnInfo(name = QUOTE_OPEN) val open: Double,
    @ColumnInfo(name = QUOTE_LAST) val last: Double,
    @ColumnInfo(name = QUOTE_HIGH) val high: Double,
    @ColumnInfo(name = QUOTE_LOW) val low: Double,
)