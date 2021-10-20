package dev.nmgalo.trukha.ui.utils.observer

interface Observable {
    fun add(observer: Observer)
    fun remove(observer: Observer)
    fun notify(message: String)
}
