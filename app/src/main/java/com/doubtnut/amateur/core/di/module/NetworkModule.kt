package com.doubtnut.amateur.core.di.module

import android.content.Context
import com.doubtnut.amateur.newsapp.BuildConfig
import com.doubtnut.amateur.core.constants.AppConstants
import com.doubtnut.amateur.core.di.scope.ApplicationScope
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class NetworkModule {

    val HTTP_CACHE_PATH = "http-cache"
    companion object{
        const val API_KEY = BuildConfig.API_KEY
    }

    @Provides
    @ApplicationScope
    fun provideEndpoint(): HttpUrl {
        return BuildConfig.BASE_URL.toHttpUrlOrNull() ?: HttpUrl.Builder()
            .scheme("https")
            .host("demo9502422.mockable.io/")
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @ApplicationScope
    fun provideRxJavaCallAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @ApplicationScope
    @Named("networkTimeoutInSeconds")
    fun provideNetworkTimeoutInSeconds(): Long {
        return AppConstants.NETWORK_CONNECTION_TIMEOUT
    }

    @Provides
    @ApplicationScope
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return loggingInterceptor
    }

    @Provides
    @ApplicationScope
    @Named("cacheSize")
    fun provideCacheSize(): Long {
        return AppConstants.CACHE_SIZE
    }

    @Provides
    @ApplicationScope
    @Named("cacheDir")
    fun provideCacheDir(@Named("applicationContext") context: Context): File {
        return context.cacheDir
    }

    @Provides
    @ApplicationScope
    fun provideCache(@Named("cacheDir") cacheDir: File,
                     @Named("cacheSize") cacheSize: Long): Cache {
        lateinit var cache: Cache

        try {
            cache = Cache(File(cacheDir.path, HTTP_CACHE_PATH), cacheSize)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return cache
    }

    @Named("okHttpClient")
    @Provides
    @ApplicationScope
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor,
                            @Named("networkTimeoutInSeconds") networkTimeoutInSeconds: Long,
                            cache: Cache
    ): OkHttpClient {

        val okHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(networkTimeoutInSeconds, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
        return okHttpClient.build()
    }


    @Provides
    @ApplicationScope
    fun provideRetrofit(baseUrl: HttpUrl,
                        converterFactory: Converter.Factory,
                        callAdapterFactory: CallAdapter.Factory,
                        @Named("okHttpClient")
                        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            //.addCallAdapterFactory(callAdapterFactory)
            .client(okHttpClient)
            .build()
    }
}