package com.ehsansetayesh.shared.movies.common.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class MetadataDTO(
    @SerializedName("current_page")
    val currentPage: String,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("page_count")
    val pageCount: Int,
    @SerializedName("total_count")
    val totalCount: Int
)