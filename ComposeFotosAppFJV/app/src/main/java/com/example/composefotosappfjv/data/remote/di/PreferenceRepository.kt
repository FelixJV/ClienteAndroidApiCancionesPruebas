package com.example.composefotosappfjv.data.remote.di

import com.example.composefotosappfjv.data.remote.dataSource.PreferenceDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferenceRepository @Inject constructor(
    private val preferenceDataStore: PreferenceDataStore
) {

    val accessToken: Flow<String?> = preferenceDataStore.accessToken

    suspend fun saveTokens(accessToken: String) {
        preferenceDataStore.saveTokens(accessToken)
    }

    suspend fun clearTokens() {
        preferenceDataStore.clearTokens()
    }
}