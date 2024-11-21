package com.ehsansetayesh.core.common.data.model.error

import java.lang.Exception

class CustomException(
    val apiError: ApiError? = null,
    val code: Int? = null,
    override val message: String? = null
) : Exception(message)
