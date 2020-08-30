package com.example.memo

import android.app.Application
import timber.log.Timber

class MemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

    }
}