package com.doubtnut.amateur.core.di.component

import com.doubtnut.amateur.core.NewsApplication
import com.doubtnut.amateur.core.di.viewmodel.ViewModelFactoryModule
import com.doubtnut.amateur.core.di.module.ActivityInjectorModule
import com.doubtnut.amateur.core.di.module.ApplicationModule
import com.doubtnut.amateur.core.di.module.ContextModule
import com.doubtnut.amateur.core.di.module.CoreServicesModule
import com.doubtnut.amateur.core.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(
    modules = [ApplicationModule::class,
        CoreServicesModule::class,
        ContextModule::class,
        AndroidSupportInjectionModule::class,
        ActivityInjectorModule::class,
        ViewModelFactoryModule::class]
)
interface ApplicationComponent {

    fun inject(newsApplication: NewsApplication)
/*
    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application,@BindsInstance applicationModule : ApplicationModule): ApplicationComponent
    }*/

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: NewsApplication): Builder

        fun build(): ApplicationComponent
    }

}