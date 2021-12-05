package com.example.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coreapi.types.ActionError
import com.example.coreapi.types.ResultWrapper
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<UIEvent, UIState, RouteEvent, StateManager : BaseStateManager<UIState>>(
    val stateManager: StateManager
) : ViewModel() {

    private val screenStateFlow: MutableStateFlow<ResultWrapper<UIState>> = MutableStateFlow(ResultWrapper.Data((stateManager.state)))
    private val loadingFlow: MutableStateFlow<ResultWrapper.Loading?> = MutableStateFlow(null)
    private val errorFlow = createSharedFlow<ResultWrapper.Error>()
    private val routeEventFlow = createSharedFlow<RouteEvent>()

    private var firstShowFlag: Boolean = false

    open val errorConsumer: (Throwable) -> Unit = {
        val error = if (it is ActionError) it.throwable else it
        postUIState(stateManager.createErrorState(it))
    }


    protected abstract fun handleUIEvent(event: UIEvent)

    protected fun postUIState(state: ResultWrapper<UIState>) {
        when (state) {
            is ResultWrapper.Data -> {
                viewModelScope.launch {
                    screenStateFlow.value = state
                }
            }
            is ResultWrapper.Loading -> {
                viewModelScope.launch {
                    loadingFlow.value = state
                }
            }
            is ResultWrapper.Error -> {
                viewModelScope.launch {
                    errorFlow.emit(state)
                }
            }
        }
    }

    protected fun postRouteEvent(event: RouteEvent) {
        viewModelScope.launch {
            routeEventFlow.emit(event)
        }
    }

    fun onFirstShow(action: () -> Unit) {
        if (!firstShowFlag) {
            firstShowFlag = true
            action()
        }
    }

    fun postEvent(event: UIEvent) {
        handleUIEvent(event)
    }

    fun postError(throwable: Throwable) {
        errorConsumer(throwable)
    }

    fun observeScreenState(): StateFlow<ResultWrapper<UIState>> = screenStateFlow
    fun observeLoadingState(): StateFlow<ResultWrapper.Loading?> = loadingFlow
    fun observeErrorState(): Flow<ResultWrapper.Error> = errorFlow
    fun observeRouteEventsState(): Flow<RouteEvent> = routeEventFlow


    private fun <T> createSharedFlow(): MutableSharedFlow<T> =
        MutableSharedFlow(
            replay = Channel.UNLIMITED,
            extraBufferCapacity = Channel.UNLIMITED,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
}