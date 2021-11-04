package com.example.coreapi.network

import com.example.coreapi.responses.QuoteResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoexApi {

    @GET("iss/engines/stock/markets/shares/boards/tqbr/securities.json?securities=SBER,MVID")
    suspend fun getLastQuotes(
        @Query("securities") tickers: List<String>,
    ): QuoteResponse

}
