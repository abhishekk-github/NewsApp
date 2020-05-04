package com.doubtnut.amateur.core.di.module

import com.doubtnut.amateur.core.di.scope.ActivityScope
import com.doubtnut.amateur.newsapp.news.NewsActivity
import com.doubtnut.amateur.newsapp.news.di.NewsModule
import com.doubtnut.amateur.newsapp.news.di.NewsViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjectorModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [NewsModule::class, NewsViewModelModule::class])
    abstract fun bindNewsActivity(): NewsActivity

}