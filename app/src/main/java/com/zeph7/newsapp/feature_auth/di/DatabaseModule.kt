package com.zeph7.newsapp.feature_auth.di

import android.content.Context
import com.zeph7.newsapp.feature_auth.data.preferences.AuthPreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAuthPreferenceManager(@ApplicationContext context: Context): AuthPreferenceManager {
        return AuthPreferenceManager(context)
    }

}