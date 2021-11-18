package dev.nmgalo.trukha.pool

import java.util.concurrent.ArrayBlockingQueue

class ThreadPool(numberOfThreads: Int, queue: Int) {

    private var taskQueue = ArrayBlockingQueue<Runnable>(queue)
    private val superThreads = List(numberOfThreads) { UberThread(taskQueue) }
    private var isStopped = false

    @Synchronized
    fun stop() {
        isStopped = true
        for (runnable in superThreads) {
            runnable.doStop()
        }
    }

    @Synchronized
    fun launch(task: Runnable) {
        taskQueue.add(task)
    }

    @Synchronized
    fun awaitAll() {
        while (taskQueue.size > 0 || superThreads.any { it.isWorking }) {
            try {
                Thread.sleep(1)
            } catch (e: InterruptedException) {
                error("Looser !!!")
            }
        }
    }

}

