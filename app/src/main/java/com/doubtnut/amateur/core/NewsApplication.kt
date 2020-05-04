package com.doubtnut.amateur.core

import android.app.Application
import com.doubtnut.amateur.core.di.component.DaggerApplicationComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class NewsApplication : Application(), HasAndroidInjector {

    @Inject
   lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>


    override fun onCreate() {

        DaggerApplicationComponent.builder()
            .application(this)
            .build()
            .inject(this)
        super.onCreate()


    }

    override fun androidInjector(): DispatchingAndroidInjector<Any> {
        return activityDispatchingAndroidInjector;
    }

}