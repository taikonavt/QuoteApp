package com.example.coreapi.types

data class ActionError(
    val throwable: Throwable,
    val action: (() -> Unit)? = null,
    val extraAction: (() -> Unit)? = null
) : Throwable()
