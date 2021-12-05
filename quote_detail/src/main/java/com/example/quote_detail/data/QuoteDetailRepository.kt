package com.example.quote_detail.data

import com.example.coreapi.database.QuoteDao
import com.example.coreapi.network.MoexApi
import kotlinx.coroutines.flow.map

class QuoteDetailRepository(private val api: MoexApi, private val dao: QuoteDao) {

    suspend fun loadQuotes(ticker: String){
        val response = api.getLastQuotes(listOf(ticker))
        dao.saveQuotes(QuoteResponseToEntityMapper(response))
    }

    fun getQuotes(ticker: String) =
        dao.getQuotes(ticker).map {
            QuoteEntityToDataMapper(it)[0]
        }

}