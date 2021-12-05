package com.example.quote_detail.data

import com.example.coreapi.entities.QuoteEntity
import com.example.quote_detail.domain.QuoteData

object QuoteEntityToDataMapper : ((List<QuoteEntity>) -> List<QuoteData>) {

    override fun invoke(entities: List<QuoteEntity>): List<QuoteData> {
        return entities.map {
            QuoteData(
                id = it.id,
                ticker = it.ticker,
                name = it.name,
                open = it.open,
                last = it.last,
                high = it.high,
                low = it.low,
            )
        }
    }

}
