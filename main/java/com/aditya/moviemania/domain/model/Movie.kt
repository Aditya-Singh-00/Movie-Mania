package com.aditya.moviemania.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val backgroundImageOriginal: String?,
    val id: Int,
    val imdb_code: String,
    val largeCoverImage: String?,
    val mediumCoverImage: String,
    val rating: Double,
    val title: String,
    val year: Int
)