package com.jww.rereapp.di

import com.jww.rereapp.main.book.BookViewModel
import com.jww.rereapp.main.movie.MovieViewModel
import com.jww.rereapp.re_evaluate.ReEvaluateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { BookViewModel(get()) }
    viewModel { ReEvaluateViewModel(get(), get()) }
}