package com.nikhilproject.presentation.di

import android.content.Context
import com.nikhilproject.presentation.SharedPreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferenceManagerModule {

    @Provides
    @Singleton
    fun providesSharedPreferenceManager(@ApplicationContext context: Context): SharedPreferenceManager {
        return SharedPreferenceManager(context)
    }
}