package com.aditya.moviemania.data.data_source.dto.common

import com.aditya.moviemania.domain.model.Movie
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val background_image_original: String? = null,
    val description_full: String,
    val genres: List<String>? = null,
    val id: Int,
    val imdb_code: String,
    val language: String,
    val large_cover_image: String? = null,
    val medium_cover_image: String,
    val rating: Double,
    val state: String,
    val summary: String,
    val synopsis: String,
    val title: String,
    val title_english: String,
    val title_long: String,
    val torrents: List<TorrentDto>,
    val year: Int,
    val yt_trailer_code: String
) {
    fun toMovie(): Movie {
        return Movie(
            backgroundImageOriginal = background_image_original,
            id = id,
            imdb_code = imdb_code,
            largeCoverImage = large_cover_image,
            mediumCoverImage = medium_cover_image,
            rating = rating,
            title = title,
            year = year
        )
    }
}