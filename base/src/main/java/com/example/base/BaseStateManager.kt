package com.example.base

import com.example.coreapi.types.ResultWrapper

abstract class BaseStateManager<State> {

    abstract var state: State

    fun createLoadingState(isShow: Boolean): ResultWrapper.Loading =
        ResultWrapper.Loading(isShow)

    fun createErrorState(throwable: Throwable): ResultWrapper.Error =
        ResultWrapper.Error(throwable)

}
