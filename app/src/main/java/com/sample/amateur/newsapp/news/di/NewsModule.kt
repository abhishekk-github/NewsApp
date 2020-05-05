package com.sample.amateur.newsapp.news.di

import com.sample.amateur.newsapp.news.db.ArticleDao
import com.sample.amateur.newsapp.news.db.ArticleDatabase
import com.sample.amateur.newsapp.news.service.NewsService
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

        @Provides
        @JvmStatic
        fun provideArticleDao(articleDatabase: ArticleDatabase): ArticleDao {
            return articleDatabase.articleDao()
        }
    }
}