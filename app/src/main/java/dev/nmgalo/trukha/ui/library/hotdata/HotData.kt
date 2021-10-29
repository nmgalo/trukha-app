package dev.nmgalo.trukha.ui.library.hotdata

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

class HotData<T> {

    private var value: T? = null
    private val observers: HashMap<(T?) -> Unit, HotDataLifecycleObserver> = HashMap()

    fun setValue(value: T) {
        this.value = value
        observers.values.forEach {
            if (it.owner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED))
                notify(it)
        }
    }

    fun getValue(): T? = value

    fun observe(owner: LifecycleOwner, observer: (T?) -> Unit) {
        val lifecycleObserver = HotDataLifecycleObserver(owner, observer)
        observers[observer] = lifecycleObserver
        owner.lifecycle.addObserver(lifecycleObserver)
    }

    fun remove(observer: (T?) -> Unit) {
        observers.remove(observer)
    }

    private fun notify(lifecycleObserver: HotDataLifecycleObserver) {
        lifecycleObserver.observer(value)
    }

    private inner class HotDataLifecycleObserver(
        val owner: LifecycleOwner,
        val observer: (T?) -> Unit
    ) : DefaultLifecycleObserver {

        override fun onStart(owner: LifecycleOwner) {
            notify(this)
        }

        override fun onResume(owner: LifecycleOwner) {
            notify(this)
        }

        override fun onDestroy(owner: LifecycleOwner) {
            remove(observer)
        }
    }

}