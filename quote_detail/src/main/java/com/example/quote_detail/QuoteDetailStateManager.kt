package com.example.quote_detail

import com.example.base.BaseStateManager
import com.example.coreapi.types.ResultWrapper
import com.example.quote_detail.model.QuoteDetailUIState

class QuoteDetailStateManager : BaseStateManager<QuoteDetailUIState>() {

    override var state = QuoteDetailUIState(
        value = "string"
    )

    fun updateItem(text: String): ResultWrapper.Data<QuoteDetailUIState> {
        state = state.copy(
            value = text
        )

        return ResultWrapper.Data(
            QuoteDetailUIState(
                value = state.value
            )
        )
    }
}