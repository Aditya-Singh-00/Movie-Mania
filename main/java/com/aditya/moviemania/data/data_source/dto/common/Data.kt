package com.aditya.moviemania.data.data_source.dto.common

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val limit: Int,
    val movie_count: Int,
    val movies: List<MovieDto>,
    val page_number: Int
)