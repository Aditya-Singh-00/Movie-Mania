package com.aditya.moviemania.data.data_source.dto.movie_search

import com.aditya.moviemania.data.data_source.dto.common.Data
import kotlinx.serialization.Serializable

@Serializable
data class MovieSearchResponse(
    val data: Data,
    val status: String,
    val status_message: String
)