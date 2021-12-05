package com.example.coreapi.ktx

import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.Window
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.coreapi.databinding.ViewPermissionsDialogBinding
import com.example.coreapi.permission.PermissionAdapter
import com.example.coreapi.permission.PermissionInformation
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Context.drawable(@DrawableRes resId: Int): Drawable? =
    ResourcesCompat.getDrawable(resources, resId, null)

fun Context.getDimension(@DimenRes resId: Int): Int =
    resources.getDimensionPixelSize(resId)

fun Context.color(@ColorRes colorId: Int): Int =
    ContextCompat.getColor(this, colorId)

fun Context.hasCameraFlash(): Boolean {
    return this.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
}

fun Context.showConfirmationDialog(
    title: String? = null,
    message: String? = null,
    positiveButtonText: String,
    positiveButtonCallback: () -> Unit,
    negativeButtonText: String,
    negativeButtonCallback: (() -> Unit)? = null
) {

    val builder = MaterialAlertDialogBuilder(this)

    with(builder) {
        title?.let { setTitle(title) }
        message?.let { setMessage(message) }
        setPositiveButton(positiveButtonText) { _, _ -> positiveButtonCallback.invoke() }
        setNegativeButton(negativeButtonText) { _, _ -> negativeButtonCallback?.invoke() }
    }

    val dialog = builder.create()
    dialog.setOnCancelListener {
        negativeButtonCallback?.invoke()
    }
    dialog.show()
}


fun Context.showPermissionDialog(
    permissionInfoList: List<PermissionInformation>,
    buttonText: String,
    buttonCallback: (Unit) -> Unit,
    cancelCallback: (Unit) -> Unit
) {
    val builder = MaterialAlertDialogBuilder(this)

    val binding = ViewPermissionsDialogBinding.inflate(LayoutInflater.from(this))

    with(builder) {
        setView(binding.root)
        setPositiveButton(buttonText) { _, _ -> buttonCallback.invoke(Unit) }
    }

    with(binding.permissionsRv) {
        adapter = PermissionAdapter(permissionInfoList)
        adapter?.notifyDataSetChanged()
    }

    val dialog = builder.create()
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setOnCancelListener {
        cancelCallback.invoke(Unit)
    }
    dialog.show()
}

fun Context.getPermissionIcon(permission: String): Drawable? {
    return when (permission) {
        else -> null
    }
}

fun Context.getPermissionsInfo(permissions: List<String>): List<PermissionInformation> {
    return permissions.map { permission ->
        val permissionInfo =
            packageManager.getPermissionInfo(permission, PackageManager.GET_META_DATA)
        PermissionInformation(
            permission = permission,
            label = permissionInfo.loadLabel(packageManager).toString(),
            description = permissionInfo.loadDescription(packageManager)?.toString(),
            icon = getPermissionIcon(permission)
        )
    }
}

fun Context.showTimePicker(
    hourOfDay: Int,
    minute: Int,
    timeSelected: (Int, Int) -> Unit,
    cancel: () -> Unit
) {
    val picker = TimePickerDialog(
        this,
        { _, _hourOfDay, _minute ->
            timeSelected.invoke(_hourOfDay, _minute)
        },
        hourOfDay,
        minute,
        true
    )
    picker.setOnCancelListener {
        cancel()
    }
    picker.setOnShowListener {
        picker.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener {
            cancel()
            picker.dismiss()
        }
    }
    picker.show()

}

fun Context.getContentInBase64FromUri(uri: Uri): String {
    this.contentResolver.openInputStream(uri).use {
        return android.util.Base64.encodeToString(it?.readBytes(), android.util.Base64.DEFAULT)
    }
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
