package dev.nmgalo.trukha.ui.utils

import android.content.Context
import androidx.annotation.StringRes
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dev.nmgalo.trukha.R


fun Context.showErrorDialog(@StringRes message: Int = R.string.error) =
    MaterialAlertDialogBuilder(this)
        .setTitle(R.string.app_name)
        .setMessage(message)
        .show()
        ?: error("Context can't be null")