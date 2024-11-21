package com.ehsansetayesh.core.common.extension

import com.ehsansetayesh.core.common.data.model.result.NetworkResult

/**
 * an extension function to handle onSuccess of api call
 */
suspend fun <T : Any> NetworkResult<T>.onSuccess(
    executable: suspend (T) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkResult.Success<T>) {
        executable(data)
    }
}

/**
 * an extension function to handle onError of api call
 */
suspend fun <T : Any> NetworkResult<T>.onError(
    executable: suspend (code: Int, message: String?) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkResult.Error<T>) {
        executable(code, message)
    }
}

/**
 * an extension function to handle onException of api call
 */
suspend fun <T : Any> NetworkResult<T>.onException(
    executable: suspend (e: Throwable) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkResult.Exception<T>) {
        executable(e)
    }
}