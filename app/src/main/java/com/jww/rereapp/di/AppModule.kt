package com.jww.rereapp.di

import android.app.Application
import com.google.gson.GsonBuilder
import com.jww.rereapp.App
import org.koin.dsl.module

fun appModule() = module {
    single { get<Application>() as App }
    single {
        GsonBuilder().create()
    }
}