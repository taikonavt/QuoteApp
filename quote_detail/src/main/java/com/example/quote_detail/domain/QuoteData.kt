package com.example.quote_detail.domain

class QuoteData(
    val id: Long,
    val ticker: String,
    val name: String,
    val open: Double,
    val last: Double,
    val high: Double,
    val low: Double,
)