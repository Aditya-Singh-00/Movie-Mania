package com.aditya.moviemania.data.data_source.dto.movies_suggestions

import kotlinx.serialization.Serializable

@Serializable
data class MovieSuggestionResponse(
    val data: Data,
    val status: String,
    val status_message: String
)