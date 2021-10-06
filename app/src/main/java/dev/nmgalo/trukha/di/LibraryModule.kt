package dev.nmgalo.trukha.di

import android.app.Application

object LibraryModule {

    @Volatile
    lateinit var app: Application

    fun initializeDI(application: Application) {
        if (!::app.isInitialized) {
            synchronized(this) {
                app = application
            }
        }
    }

}

