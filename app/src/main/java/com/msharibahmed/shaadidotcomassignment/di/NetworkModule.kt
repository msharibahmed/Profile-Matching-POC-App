package com.msharibahmed.shaadidotcomassignment.di

import com.msharibahmed.shaadidotcomassignment.data.remote.api.IApiService
import com.msharibahmed.shaadidotcomassignment.utils.AppConstants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Singleton
    @Provides
    fun provideHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()


    @Singleton
    @Provides
    fun getRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(httpClient).build()

    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): IApiService = retrofit
        .create(IApiService::class.java)

}
