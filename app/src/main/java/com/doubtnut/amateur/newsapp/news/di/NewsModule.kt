package com.doubtnut.amateur.newsapp.news.di

import com.doubtnut.amateur.newsapp.news.service.NewsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class NewsModule {
    companion object {
        @Provides
        @JvmStatic
        fun provideNewsService(retrofit: Retrofit): NewsService =
            retrofit.create(NewsService::class.java)
    }
}