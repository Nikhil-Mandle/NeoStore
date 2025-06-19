package com.nikhilproject.presentation.di

import android.content.Context
import com.nikhilproject.presentation.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TokenManagerModule {

    @Provides
    @Singleton
    fun providesTokenManager(@ApplicationContext context: Context): TokenManager {
        return TokenManager(context)
    }
}