package com.jww.rereapp.di

import com.jww.rereapp.main.movie.ui.MovieUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { MovieUseCase(get()) }
}