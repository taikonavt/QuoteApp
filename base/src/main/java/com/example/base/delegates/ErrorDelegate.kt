package com.example.base.delegates

import android.app.Activity
import android.widget.Toast
import com.example.base.R
import com.example.coreapi.ktx.showConfirmationDialog
import com.example.coreapi.ktx.showSimpleDialog
import com.example.coreapi.ktx.showSnackBar
import com.example.coreapi.types.ActionError
import javax.inject.Inject

interface ErrorDelegate {

    fun bindErrorActivity(activity: Activity)

    fun showError(throwable: Throwable)
}

class ErrorDelegateImpl : ErrorDelegate {

    private lateinit var activity: Activity

    private val repeat by lazy { activity.getString(R.string.repeat) }
    private val yes by lazy { activity.getString(R.string.yes) }
    private val no by lazy { activity.getString(R.string.no) }

    override fun bindErrorActivity(activity: Activity) {
        this.activity = activity
    }

    override fun showError(throwable: Throwable) {
        processError(throwable)
    }

    private fun processError(throwable: Throwable) {
        @Suppress("ThrowableNotThrown") val error = this.getError(throwable)
        val message = getErrorMessage(throwable)
        val action: (() -> Unit)? = getAction(throwable)
        when (error) {
            else ->
                if (action == null) showDialog(message)
                else showSnackBar(message, action)
        }
    }

    private fun getErrorMessage(throwable: Throwable): String {
        return when (throwable) {
            is ActionError -> getErrorMessage(throwable.throwable)
            else -> ""
        }
    }

    private fun getError(throwable: Throwable): Throwable {
        return when (throwable) {
            is ActionError -> throwable.throwable
            else -> throwable
        }
    }

    private fun getAction(throwable: Throwable): (() -> Unit)? {
        return when (throwable) {
            is ActionError -> if (isForceError(throwable)) throwable.extraAction else throwable.action
            else -> null
        }
    }

    private fun getErrorMessageWithErrorId(message: String, errorId: String?) =
        "$message${if (errorId != null) "\n(${errorId})" else ""}"

    private fun showSnackBar(message: String, action: (() -> Unit)? = null) {
        activity.showSnackBar(message, repeat, action)
    }

    private fun showDialog(message: String, title: String? = null) {
        activity.showSimpleDialog(message = message, title = title)
    }

    private fun showDialogWithTwoButtons(message: String, actionYes: (() -> Unit)?, title: String?) {
        activity.showConfirmationDialog(
            title = title,
            message = message,
            positiveButtonText = yes,
            negativeButtonText = no,
            positiveButtonCallback = { actionYes?.invoke() }
        )
    }

    private fun showToast(message: String) {
        Toast.makeText(activity.applicationContext, message, Toast.LENGTH_LONG).show()
    }

    private fun isForceError(actionError: ActionError): Boolean {
//        return (actionError.throwable is MrmHttpException
//                && (actionError.throwable as MrmHttpException).responseCode == ERROR_432)

        return false
    }
}
