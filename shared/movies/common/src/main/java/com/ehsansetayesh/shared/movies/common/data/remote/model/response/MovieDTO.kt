package com.ehsansetayesh.shared.movies.common.data.remote.model.response

import com.ehsansetayesh.shared.movies.common.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("poster")
    val poster: String,
    @SerializedName("year")
    val year: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("imdb_rating")
    val imdbRating: String,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("images")
    val images: List<String>
)

fun MovieDTO.toDomainModel(): Movie = Movie(
    id = id.toString(),
    title = title,
    poster = poster,
    year = year,
    country = country,
    imdbRating = imdbRating,
    genres = genres,
    images = images
)