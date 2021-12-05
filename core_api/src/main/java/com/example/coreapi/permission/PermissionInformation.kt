package com.example.coreapi.permission

import android.graphics.drawable.Drawable

data class PermissionInformation(
    val permission: String,
    val label: String? = null,
    val description: String? = null,
    val icon: Drawable? = null
)
