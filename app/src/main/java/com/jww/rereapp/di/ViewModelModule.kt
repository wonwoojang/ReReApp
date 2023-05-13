package com.jww.rereapp.di

import com.jww.rereapp.main.movie.MovieViewModel
import com.jww.rereapp.main.webToon.WebToonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { WebToonViewModel(get()) }
}