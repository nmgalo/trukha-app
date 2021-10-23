package dev.nmgalo.trukha.ui.utils.observer

interface ISubject<T> {
    fun registerObserver(observer: Observer<T>)
    fun remove(observer: Observer<T>)
    fun notify(data: T)
}

class Subject<T> : ISubject<T> {
    private val list = mutableListOf<Observer<T>>()

    override fun registerObserver(observer: Observer<T>) {
        list.add(observer)
    }

    override fun remove(observer: Observer<T>) {
        val index = list.indexOf(observer)
        if (index >= 0)
            list.removeAt(index)
    }

    override fun notify(data: T) {
        list.forEach {
            it.update(data)
        }
    }

}
