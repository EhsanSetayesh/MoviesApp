package com.ehsansetayesh.core.common.data.model.error

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ApiError(
    @SerializedName("code") val code: String,
    @SerializedName("message") val message: String,
    @SerializedName("details") val details: List<ErrorDetail>
) : Serializable {
    companion object {
        val DEFAULT = ApiError(code = "", message = "Error Message", details = listOf())
        fun emptyError() = ApiError("", "", listOf())
    }
}

fun ApiError.toCustomError() = CustomError(
    code = this.code,
    message = this.message,
    details = this.details.map {
        it.toCustomErrorDetail()
    }
)

class ErrorDetail(
    @SerializedName("@type") val type: String,
    @SerializedName("field") val field: String,
    @SerializedName("describ") val description: String,
    @SerializedName("message") val message: String?
) : Serializable

fun ErrorDetail.toCustomErrorDetail() = CustomErrorDetail(
    type = this.type,
    field = this.field,
    description = this.description,
    message = this.message
)