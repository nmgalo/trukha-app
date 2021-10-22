package dev.nmgalo.trukha.ui.utils.observer

fun interface Observer<T> {
    fun update(data: T)
}
