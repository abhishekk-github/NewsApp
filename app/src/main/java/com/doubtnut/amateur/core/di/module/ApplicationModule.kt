package com.doubtnut.amateur.core.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.doubtnut.amateur.core.NewsApplication
import com.doubtnut.amateur.core.di.scope.ApplicationScope
import com.doubtnut.amateur.newsapp.news.db.ArticleDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ApplicationModule() {

    @Provides
    @ApplicationScope
    fun provideApplication(application: NewsApplication): Application {
        return application
    }

    @Named("applicationContext")
    @Provides
    @ApplicationScope
    fun provideApplicationContext(application: NewsApplication): Context {
        return application.applicationContext
    }

    @Provides
    @ApplicationScope
    fun provideArticleDatabase(context: Context): ArticleDatabase {
        return Room.databaseBuilder(context, ArticleDatabase::class.java, "articles.db")
            .build()
    }
}