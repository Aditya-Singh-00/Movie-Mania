package com.aditya.moviemania.data.data_source.dto.movies

import com.aditya.moviemania.data.data_source.dto.common.Data
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val data: Data,
    val status: String,
    val status_message: String
)