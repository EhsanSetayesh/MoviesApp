package com.ehsansetayesh.shared.movies.common.domain.model

data class Movie(
    val id: String,
    val title: String,
    val poster: String,
    val year: String,
    val country: String,
    val imdbRating: String,
    val genres: List<String>,
    val images: List<String>
)
