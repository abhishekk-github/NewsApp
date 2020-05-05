package com.sample.amateur.newsapp.news.di

import androidx.lifecycle.ViewModel
import com.sample.amateur.core.di.viewmodel.ViewModelKey
import com.sample.amateur.newsapp.news.repository.NewsRepository
import com.sample.amateur.newsapp.news.repository.NewsRepositoryImpl
import com.sample.amateur.newsapp.news.viewmodel.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class NewsViewModelModule {

    @Binds
    internal abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(viewModel: NewsViewModel) : ViewModel
}