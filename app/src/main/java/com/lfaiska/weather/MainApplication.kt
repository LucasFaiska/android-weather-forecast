package com.lfaiska.weather

import android.app.Application
import com.lfaiska.weather.data.di.dataModule
import com.lfaiska.weather.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(appModule, dataModule))
        }
    }
}