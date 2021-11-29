package com.aditya.moviemania.data.data_source.dto.movies_details

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailResponse(
    val data: Data,
    val status: String,
    val status_message: String
)