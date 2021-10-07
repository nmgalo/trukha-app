package dev.nmgalo.trukha.ui.utils

interface Observerable {
    fun addObserver(myObserver: Observer?)
    fun removeObserver(myObserver: Observer?)
    fun notifyObservers(change: Boolean)
}