package dev.nmgalo.trukha.ui.utils

import android.os.Handler
import android.os.Looper

inline fun delay(delayMillis: Long, crossinline block: () -> Unit) =
    Handler(Looper.getMainLooper()).postDelayed({ block() }, delayMillis)
