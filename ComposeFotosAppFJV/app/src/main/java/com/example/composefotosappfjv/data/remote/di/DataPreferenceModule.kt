package com.example.composefotosappfjv.data.remote.di

import android.content.Context
import com.example.composefotosappfjv.data.remote.dataSource.PreferenceDataStore
import com.example.composefotosappfjv.data.remote.di.PreferenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providePreferenceDataStore(@ApplicationContext context: Context): PreferenceDataStore {
        return PreferenceDataStore(context)
    }

    @Provides
    @Singleton
    fun providePreferencesRepository(preferenceDataStore: PreferenceDataStore): PreferenceRepository {
        return PreferenceRepository(preferenceDataStore)
    }
}
