package com.doubtnut.amateur.core.di.module

import android.app.Application
import dagger.Module

@Module(includes = [NetworkModule::class, AnalyticsModule::class, AuthModule::class])
abstract class CoreServicesModule(private val application: Application) {
}