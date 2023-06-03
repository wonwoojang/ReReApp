package com.jww.rereapp.di

import com.jww.rereapp.common.repository.MovieRepository
import com.jww.rereapp.common.repository.NaverSearchRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepository.Implement(get(), get()) }
    single<NaverSearchRepository> { NaverSearchRepository.Implement(get(), get()) }
}