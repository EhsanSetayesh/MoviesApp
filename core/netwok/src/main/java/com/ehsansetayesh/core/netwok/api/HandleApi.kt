package com.ehsansetayesh.core.netwok.api

import com.ehsansetayesh.core.common.data.model.error.ApiError
import com.ehsansetayesh.core.common.data.model.result.NetworkResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException
import retrofit2.Response

/**
 * A suspend function is to convert Retrofit [Response] into [NetworkResult]
 *
 * @param execute a suspend function which returns Retrofit Response
 * @param gson to get custom error model from BE
 * @return [NetworkResult]
 */
internal suspend fun <T : Any> handleApi(
    gson: Gson,
    execute: suspend () -> Response<T>
): NetworkResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            NetworkResult.Success(body)
        } else {
            val apiError = if (response.errorBody() != null) {
                // converting json error in to ApiError
                val type = object : TypeToken<ApiError>() {}.type
                val errorResponse: ApiError? =
                    gson.fromJson(response.errorBody()!!.charStream(), type)
                errorResponse
            } else {
                null
            }
            NetworkResult.Error(
                code = response.code(), message = response.message()
            )
        }
    } catch (e: HttpException) {
        NetworkResult.Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        NetworkResult.Exception(e)
    }
}