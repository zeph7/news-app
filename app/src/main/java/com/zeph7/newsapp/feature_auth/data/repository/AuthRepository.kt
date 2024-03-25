package com.zeph7.newsapp.feature_auth.data.repository

import com.zeph7.newsapp.feature_auth.data.preferences.AuthPreferenceManager
import com.zeph7.newsapp.feature_auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val preferenceManager: AuthPreferenceManager
) : AuthRepository {

    override suspend fun setAppEntry() {
        preferenceManager.setAppEntry()
    }

    override suspend fun getAppEntry(): Boolean {
        return preferenceManager.getAppEntry()
    }
}