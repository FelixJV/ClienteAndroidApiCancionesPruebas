package com.example.composefotosappfjv.data.remote.dataSource

import com.example.composefotosappfjv.data.remote.NetworkResult
import retrofit2.Response
import timber.log.Timber

open class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            Timber.e("Error en la llamada")
            return error(e.message ?: e.toString())
        }
    }
    suspend fun <T> safeApiCallNoBody(apiCall: suspend () -> Response<T>): NetworkResult<Boolean> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                return NetworkResult.Success(true)
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }
    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Api call failed $errorMessage")
}