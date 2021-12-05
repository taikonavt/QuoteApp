package com.example.quote_detail

import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.quote_detail.domain.QuoteDetailInteractor
import com.example.quote_detail.model.QuoteDetailRouteEvent
import com.example.quote_detail.model.QuoteDetailUIEvent
import com.example.quote_detail.model.QuoteDetailUIState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class QuoteDetailViewModel(stateManager: QuoteDetailStateManager, val interactor: QuoteDetailInteractor) :
    BaseViewModel<QuoteDetailUIEvent, QuoteDetailUIState, QuoteDetailRouteEvent, QuoteDetailStateManager>(stateManager) {

    override fun handleUIEvent(event: QuoteDetailUIEvent) {
        when(event) {
            is QuoteDetailUIEvent.Init -> handleInit()
        }
    }

    private fun handleInit() {
        viewModelScope.launch {
            interactor.loadQuotes("SBER")
            interactor.getQuotes("SBER")
                .collect {
                    postUIState(stateManager.updateItem(it.last.toString()))
                }
        }
    }

}