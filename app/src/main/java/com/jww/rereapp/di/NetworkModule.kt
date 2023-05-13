package com.jww.rereapp.di

import com.jww.rereapp.common.service.MovieService
import com.jww.rereapp.common.service.WebToonService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val MOVIE_BASE_URL = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/"
const val WEB_TOON_URL = "http://서비스URL주소/openapi/search/"
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
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
//
//    factory {
//        Retrofit.Builder().client(get()).baseUrl("")
//            .addConverterFactory(GsonConverterFactory.create(get()))
//            .build()
//    }


    single {
        val movieRetrofit =
            Retrofit.Builder()
                .client(get())
                .baseUrl(MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(get()))
                .build()

        movieRetrofit.create(MovieService::class.java)
    }

    single {
        val webToonRetrofit =
            Retrofit.Builder()
                .client(get())
                .baseUrl(WEB_TOON_URL)
                .addConverterFactory(GsonConverterFactory.create(get()))
                .build()

        webToonRetrofit.create(WebToonService::class.java)
    }
}