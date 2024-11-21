package com.ehsansetayesh.core.common.data.model.error

data class CustomError(
    val code: String? = null,
    val message: String? = null,
    val details: List<CustomErrorDetail>? = null
)

data class CustomErrorDetail(
    val type: String,
    val field: String,
    val description: String,
    val message: String?
)