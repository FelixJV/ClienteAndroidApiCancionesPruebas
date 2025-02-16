package com.example.composefotosappfjv.data.remote.di

import com.example.composefotosappfjv.data.remote.apiServices.LoginResponse
import com.example.composefotosappfjv.data.remote.dataSource.PreferenceDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferenceRepository @Inject constructor(
    private val preferenceDataStore: PreferenceDataStore
) {

    suspend fun saveTokens(loginResponse: LoginResponse) {
        preferenceDataStore.saveTokens(loginResponse)
    }

    suspend fun clearTokens() {
        preferenceDataStore.clearTokens()
    }
}