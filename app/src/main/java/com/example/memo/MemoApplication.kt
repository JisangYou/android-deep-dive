package com.example.memo

import android.app.Application
import dagger.Component


@Component
class MemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

    }
}