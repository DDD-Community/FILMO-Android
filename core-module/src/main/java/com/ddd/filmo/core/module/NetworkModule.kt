package com.ddd.filmo.core.module

import com.ddd.filmo.network.KmdbAPI
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val json by lazy {
        Json {
            coerceInputValues = true
            ignoreUnknownKeys = true
        }
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): Interceptor = HttpRequestInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://15.164.186.97")
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType()),
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideKmdbAPI(
        retrofit: Retrofit,
    ): KmdbAPI = retrofit.create(KmdbAPI::class.java)
}
