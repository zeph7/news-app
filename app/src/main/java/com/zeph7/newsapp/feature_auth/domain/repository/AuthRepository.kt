package com.zeph7.newsapp.feature_auth.domain.repository

interface AuthRepository {
    suspend fun setAppEntry()
    suspend fun getAppEntry(): Boolean
}