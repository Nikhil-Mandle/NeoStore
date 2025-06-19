package com.nikhilproject.data.di

import com.nikhilproject.data.api.CartApiService
import com.nikhilproject.data.api.OrderApiService
import com.nikhilproject.data.api.ProductApiService
import com.nikhilproject.data.api.UserApiService
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
object DataModule {
    @Provides
    @Singleton
    fun okHttp() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    @Provides
    @Singleton
    fun retrofit(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl("http://staging.php-dev.in:8844/trainingapp/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun userApi(retrofit: Retrofit) = retrofit.create(UserApiService::class.java)

    @Provides
    fun productApi(retrofit: Retrofit) = retrofit.create(ProductApiService::class.java)

    @Provides
    fun cartApi(retrofit: Retrofit) = retrofit.create(CartApiService::class.java)

    @Provides
    fun orderApi(retrofit: Retrofit) = retrofit.create(OrderApiService::class.java)
}