package com.zeph7.newsapp.feature_auth.di

import com.zeph7.newsapp.feature_auth.data.preferences.AuthPreferenceManager
import com.zeph7.newsapp.feature_auth.data.repository.AuthRepositoryImpl
import com.zeph7.newsapp.feature_auth.domain.repository.AuthRepository
import com.zeph7.newsapp.feature_auth.domain.use_case.GetAppEntryUseCase
import com.zeph7.newsapp.feature_auth.domain.use_case.OnboardingUseCases
import com.zeph7.newsapp.feature_auth.domain.use_case.SetAppEntryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthRepository(preferenceManager: AuthPreferenceManager): AuthRepository {
        return AuthRepositoryImpl(preferenceManager)
    }

    @Provides
    @Singleton
    fun provideOnboardingUseCases(repository: AuthRepositoryImpl): OnboardingUseCases {
        return OnboardingUseCases(
            setAppEntry = SetAppEntryUseCase(repository),
            getAppEntry = GetAppEntryUseCase(repository)
        )
    }

}