package com.example.composefotosappfjv.data.remote.utils

import com.example.composefotosappfjv.data.remote.apiServices.LoginResponse
import com.example.composefotosappfjv.data.remote.apiServices.UserService
import com.example.composefotosappfjv.data.remote.dataSource.PreferenceDataStore
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import dagger.Lazy

class AuthAuthenticator @Inject constructor(
    private val tokenManager: PreferenceDataStore,
    private val service : Lazy<UserService>,
): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking {
            tokenManager.getRefreshToken()
        }
        return runBlocking {
            val newToken = getNewToken(token.toString())

            if (!newToken.isSuccessful || newToken.body() == null) {
                tokenManager.clearTokens()
            }

            newToken.body()?.let {
                tokenManager.saveTokens(it)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.accessToken}")
                    .build()
            }
        }
    }

    private suspend fun getNewToken(refreshToken: String?): retrofit2.Response<LoginResponse> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return service.get().refreshToken("Bearer $refreshToken")
    }
}
