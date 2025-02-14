package com.example.composefotosappfjv.data.remote.dataSource

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_preferences")

class PreferenceDataStore(private val context: Context) {
    companion object {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        val USERNAME = stringPreferencesKey("username")
    }

    val accessToken: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[ACCESS_TOKEN]
    }

    suspend fun saveTokens(accessToken: String, refreshToken: String) {
        context.dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken
            preferences[REFRESH_TOKEN] = refreshToken
        }
    }

    suspend fun clearTokens() {
        context.dataStore.edit { it.clear() }
    }
}
