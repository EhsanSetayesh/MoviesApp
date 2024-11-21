package com.ehsansetayesh.shared.movies.common.data.remote.model.response

import com.ehsansetayesh.shared.movies.common.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MoviesResponseDTO(
    @SerializedName("data")
    val moviesList: List<MovieDTO>,
    @SerializedName("metadata")
    val metadata: MetadataDTO
)

fun MoviesResponseDTO.toDomainModel(): List<Movie> {
    return moviesList.map { it.toDomainModel() }
}