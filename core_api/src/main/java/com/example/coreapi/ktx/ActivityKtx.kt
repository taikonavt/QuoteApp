package com.example.coreapi.ktx

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.ContentFrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.core.view.isNotEmpty
import com.example.coreapi.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

fun Activity.hideSoftKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}

fun Activity.createDrawable(@DrawableRes drawableInt: Int): Drawable? {
    return ContextCompat.getDrawable(this, drawableInt)
}

fun Activity.showSnackBar(
    message: String,
    actionName: String? = null,
    action: (() -> Unit)? = null
) {
    val contentFrameLayout = findViewById<ContentFrameLayout>(android.R.id.content)
    val snackBarParentView = if (contentFrameLayout.isNotEmpty()) {
        contentFrameLayout[0]
    } else {
        contentFrameLayout
    }
    Snackbar.make(
        snackBarParentView,
        message,
        if (action == null) Snackbar.LENGTH_LONG else Snackbar.LENGTH_INDEFINITE
    ).apply {
        view.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        action?.let { setAction(actionName) { it() } }
    }.show()
}

fun Activity.showSimpleDialog(
    title: String? = null,
    message: String,
    action: (() -> Unit)? = null,
    dismissListener: (() -> Unit)? = null
) {
    MaterialAlertDialogBuilder(this)
        .apply {
            setCancelable(false)
            setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                action?.invoke()
                dialog.cancel()
            }
            title?.let { this.setTitle(it) }
            setMessage(message)
            setOnDismissListener {
                dismissListener?.invoke()
            }
        }.show()
}

fun Activity?.getScreenHeightWithoutNavigationAndStatusBar(): Int? {
    val contentFrameLayout = this?.findViewById<ContentFrameLayout>(android.R.id.content)
    contentFrameLayout?.let {
       if (it.measuredHeight != 0) {
            return it.measuredHeight - this!!.getStatusBarHeight()
       }
    }
    return null
}

fun Activity.getStatusBarHeight(): Int {
    val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        return resources.getDimensionPixelSize(resourceId)
    }
    return 0
}
