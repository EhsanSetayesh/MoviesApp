package com.ehsansetayesh.shared.movies.common.domain.model

data class MovieDetails(
    val id: String,
    val title: String,
    val poster: String,
    val year: String,
    val rated: String,
    val released: String,
    val runtime: String,
    val director: String,
    val writer: List<String>,
    val actors: List<String>,
    val plot: String,
    val country: String,
    val awards: String,
    val metascore: Int,
    val imdbRating: String,
    val imdbVotes: Int,
    val genres: List<String>,
    val images: List<String>
)

fun MovieDetails.toMovie(): Movie = Movie(
    id = id,
    title = title,
    poster = poster,
    year = year,
    country = country,
    imdbRating = imdbRating,
    genres = genres,
    images = images
)