package com.sample.amateur.core.di.module

import com.sample.amateur.core.di.scope.ActivityScope
import com.sample.amateur.newsapp.news.NewsActivity
import com.sample.amateur.newsapp.news.di.NewsModule
import com.sample.amateur.newsapp.news.di.NewsViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjectorModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [NewsModule::class, NewsViewModelModule::class])
    abstract fun bindNewsActivity(): NewsActivity

}