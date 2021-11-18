package dev.nmgalo.trukha.pool

import java.util.concurrent.ArrayBlockingQueue

class UberThread(
    private val taskQueue: ArrayBlockingQueue<Runnable>
) {

    private var thread: Thread

    @Volatile
    var isStopped = false

    @Volatile
    var isWorking = false

    init {
        thread = Thread {
            while (!isStopped) {
                val task = taskQueue.take()
                try {
                    isWorking = true
                    task.run()
                    isWorking = false
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        thread.start()
    }

    @Synchronized
    fun doStop() {
        isStopped = true
        thread.interrupt()
    }
}