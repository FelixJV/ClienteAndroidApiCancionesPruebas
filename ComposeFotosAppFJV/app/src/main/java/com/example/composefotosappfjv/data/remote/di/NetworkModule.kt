package com.example.composefotosappfjv.data.remote.di

import com.example.compose.BuildConfig
import com.example.composefotosappfjv.data.remote.apiServices.CancionService
import com.example.composefotosappfjv.data.remote.apiServices.UserService
import com.example.composefotosappfjv.data.remote.dataSource.PreferenceDataStore
import com.example.composefotosappfjv.data.remote.utils.AuthAuthenticator
import com.example.composefotosappfjv.data.remote.utils.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import dagger.Lazy
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAuthAuthenticator(
        tokenManager: PreferenceDataStore,
        userService: Lazy<UserService>  
    ): AuthAuthenticator {
        return AuthAuthenticator(tokenManager, userService)
    }

    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        authAuthenticator: AuthAuthenticator
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .authenticator(authAuthenticator)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    fun provideFotoService(retrofit: Retrofit): CancionService {
        return retrofit.create(CancionService::class.java)
    }
}
