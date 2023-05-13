package com.jww.rereapp.di

import com.jww.rereapp.main.movie.ui.MovieUseCase
import com.jww.rereapp.main.webToon.WebToonUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { MovieUseCase(get()) }
    single { WebToonUseCase(get()) }
}