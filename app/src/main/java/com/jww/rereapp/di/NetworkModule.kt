package com.jww.rereapp.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun networkModule(
    connectTimeoutSeconds: Long = 30L,
    readTimeoutSeconds: Long = 30L,
    writeTimeoutSeconds: Long = 180L
) = module {
    single {
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    factory {
        OkHttpClient.Builder().connectTimeout(connectTimeoutSeconds, TimeUnit.SECONDS)
            .readTimeout(readTimeoutSeconds, TimeUnit.SECONDS)
            .writeTimeout(writeTimeoutSeconds, TimeUnit.SECONDS)
            .addInterceptor(get<HttpLoggingInterceptor>()).build()
    }

    factory {
        Retrofit.Builder().client(get()).baseUrl("")
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }
}