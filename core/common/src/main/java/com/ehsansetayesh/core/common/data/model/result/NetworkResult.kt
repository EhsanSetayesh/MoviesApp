package com.ehsansetayesh.core.common.data.model.result

import com.ehsansetayesh.core.common.data.model.error.ApiError

/**
 * A sealed interface to model Retrofit Responses
 */
sealed interface NetworkResult<T : Any> {
    data class Success<T : Any>(val data: T) : NetworkResult<T>
    data class Error<T : Any>(val code: Int, val message: String?) :
        NetworkResult<T>
    data class Exception<T : Any>(val e: Throwable) : NetworkResult<T>
}