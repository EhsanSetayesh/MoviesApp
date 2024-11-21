package com.ehsansetayesh.shared.movies.common.data.remote.model.response

import com.ehsansetayesh.shared.movies.common.domain.model.MovieDetails
import com.google.gson.annotations.SerializedName

data class MovieDetailsDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("poster")
    val poster: String,
    @SerializedName("year")
    val year: String,
    @SerializedName("rated")
    val rated: String,
    @SerializedName("released")
    val released: String,
    @SerializedName("runtime")
    val runtime: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("writer")
    val writer: String,
    @SerializedName("actors")
    val actors: String,
    @SerializedName("plot")
    val plot: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("awards")
    val awards: String,
    @SerializedName("metascore")
    val metascore: String,
    @SerializedName("imdb_rating")
    val imdbRating: String,
    @SerializedName("imdb_votes")
    val imdbVotes: String,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("images")
    val images: List<String>
)

// Extension function to map DTO to domain model
fun MovieDetailsDTO.toDomainModel(): MovieDetails = MovieDetails(
    id = id.toString(),
    title = title,
    poster = poster,
    year = year,
    rated = rated,
    released = released,
    runtime = runtime,
    director = director,
    actors = actors.split(", ").filter { it.isNotBlank() },
    plot = plot,
    country = country,
    awards = awards,
    imdbRating = imdbRating,
    genres = genres,
    images = images,
    metascore = metascore.toIntOrNull() ?: 0,
    imdbVotes = imdbVotes.replace(",", "").toIntOrNull() ?: 0,
    writer = writer.split(", ").filter { it.isNotBlank() }
)