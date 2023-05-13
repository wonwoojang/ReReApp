package com.jww.rereapp

import android.app.Application
import com.jww.rereapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger()
            koin.loadModules(
                listOf(
                    appModule(),
                    networkModule(),
                    viewModelModule,
                    repositoryModule,
                    useCaseModule
                )
            )
        }
    }
}