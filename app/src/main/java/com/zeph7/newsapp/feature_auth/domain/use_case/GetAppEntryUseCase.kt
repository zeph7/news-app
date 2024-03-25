package com.zeph7.newsapp.feature_auth.domain.use_case

import com.zeph7.newsapp.feature_auth.domain.repository.AuthRepository

class GetAppEntryUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): Boolean {
        return repository.getAppEntry()
    }
}