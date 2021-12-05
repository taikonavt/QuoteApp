package com.example.quote_detail.data

import com.example.coreapi.entities.QuoteEntity
import com.example.coreapi.responses.MarketdataColumn
import com.example.coreapi.responses.QuoteResponse
import com.example.coreapi.responses.SecurityColumn

object QuoteResponseToEntityMapper : ((QuoteResponse) -> List<QuoteEntity>) {

    override fun invoke(response: QuoteResponse): List<QuoteEntity> {
        val secTickerIndex = response.securities.columns.indexOf(SecurityColumn.SECID)
        val nameIndex = response.securities.columns.indexOf(SecurityColumn.SHORTNAME)

        val marketTickerIndex = response.marketdata.columns.indexOf(MarketdataColumn.SECID)
        val openIndex = response.marketdata.columns.indexOf(MarketdataColumn.OPEN)
        val lastIndex = response.marketdata.columns.indexOf(MarketdataColumn.LAST)
        val highIndex = response.marketdata.columns.indexOf(MarketdataColumn.HIGH)
        val lowIndex = response.marketdata.columns.indexOf(MarketdataColumn.LOW)

        return response.securities.data.map { item ->
            val ticker = item[secTickerIndex] as String
            val name = item[nameIndex] as String
            val itemMarketData = response.marketdata.data.first { item[secTickerIndex] == it[marketTickerIndex] }

            QuoteEntity(
                ticker = ticker,
                name = name,
                open = itemMarketData[openIndex] as Double,
                last = itemMarketData[lastIndex] as Double,
                high = itemMarketData[highIndex] as Double,
                low = itemMarketData[lowIndex] as Double
            )
        }
    }
}