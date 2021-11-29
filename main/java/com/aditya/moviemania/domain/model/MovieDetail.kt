package com.aditya.moviemania.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetail(
    val backgroundImageOriginal: String,
    val cast: List<Cast>,
    val descriptionFull: String,
    val genres: List<String>,
    val id: Int,
    val imdbCode: String,
    val language: String,
    val mediumCoverImage: String,
    val mediumScreenshotImage1: String? = null,
    val mediumScreenshotImage2: String? = null,
    val mediumScreenshotImage3: String? = null,
    val mpaRating: String,
    val rating: Double,
    val runtime: Int,
    val title: String,
    val torrents: List<Torrent>,
    val url: String,
    val year: Int,
    val ytTrailerCode: String
)