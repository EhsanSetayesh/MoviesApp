package com.ehsansetayesh.core.common.data.model.result

import com.ehsansetayesh.core.common.data.model.error.CustomError
import com.ehsansetayesh.core.common.data.model.error.CustomException
import com.ehsansetayesh.core.common.data.model.error.toCustomError

/**
 * ResultWrapper Wrapper <Left = Exception, Right = Value/Success>
 */
sealed interface Result<out E, out V> {

    data class Value<out V>(val value: V) : Result<Nothing, V>
    data class Error<out E>(val error: E) : Result<E, Nothing>

    companion object Factory {
        //higher order functions take functions as parameters or return a function
        //Kotlin has function types name: () -> V
        inline fun <V> build(function: () -> V): Result<CustomError, V> = try {
            Value(function.invoke())
        } catch (e: CustomException) {
            val customError = e.apiError?.toCustomError() ?: CustomError(
                code = e.code?.toString(), message = e.message, details = null
            )
            Error(customError)
        }
    }
}