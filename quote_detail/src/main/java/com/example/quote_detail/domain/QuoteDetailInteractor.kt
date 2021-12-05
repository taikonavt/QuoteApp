package com.example.quote_detail.domain

import com.example.quote_detail.data.QuoteDetailRepository
import kotlinx.coroutines.flow.Flow

class QuoteDetailInteractor(private val repository: QuoteDetailRepository) {

    suspend fun loadQuotes(ticker: String) {
        repository.loadQuotes(ticker)
    }

    fun getQuotes(ticker: String) : Flow<QuoteData> =
        repository.getQuotes(ticker)
}