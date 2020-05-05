package com.sample.amateur.core.di.component

import com.sample.amateur.core.NewsApplication
import com.sample.amateur.core.di.module.ActivityInjectorModule
import com.sample.amateur.core.di.module.ApplicationModule
import com.sample.amateur.core.di.module.ContextModule
import com.sample.amateur.core.di.module.CoreServicesModule
import com.sample.amateur.core.di.scope.ApplicationScope
import com.sample.amateur.core.di.viewmodel.ViewModelFactoryModule
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

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: NewsApplication): Builder

        fun build(): ApplicationComponent
    }

}