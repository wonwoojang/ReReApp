package com.jww.rereapp.di

import com.jww.rereapp.common.repository.MovieRepository
import com.jww.rereapp.common.repository.WebToonRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepository.Implement(get(), get()) }
    single<WebToonRepository> { WebToonRepository.Implement(get(), get()) }
}