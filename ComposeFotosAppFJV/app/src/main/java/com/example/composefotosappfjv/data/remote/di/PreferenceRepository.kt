package com.example.composefotosappfjv.data.remote.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.composefotosappfjv.data.remote.dataSource.PreferenceDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferenceRepository @Inject constructor(
    private val preferenceDataStore: PreferenceDataStore
) {

    val accessToken: Flow<String?> = preferenceDataStore.accessToken


    suspend fun saveTokens(accessToken: String, refreshToken: String) {
        preferenceDataStore.saveTokens(accessToken, refreshToken)
    }

    suspend fun clearTokens() {
        preferenceDataStore.clearTokens()
    }
}