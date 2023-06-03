package com.jww.rereapp.di

import com.jww.rereapp.main.book.BookUseCase
import com.jww.rereapp.main.movie.MovieUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { MovieUseCase(get()) }
    single { BookUseCase(get()) }
}