package dev.nmgalo.trukha.ui.utils.observer

interface ISubject<T> {
    fun register(observer: Observer<T>)
    fun remove(observer: Observer<T>)
    fun notify(data: T)
}

class Subject<T> : ISubject<T> {
    private val list = mutableListOf<Observer<T>>()

    override fun register(observer: Observer<T>) {
        list.add(observer)
    }

    override fun remove(observer: Observer<T>) {
        val i = list.indexOf(observer)
        if (i >= 0)
            list.removeAt(i)
    }

    override fun notify(data: T) {
        list.map {
            it.update(data)
        }
    }

}