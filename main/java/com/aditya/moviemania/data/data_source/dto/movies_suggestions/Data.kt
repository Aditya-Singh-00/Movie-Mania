package com.aditya.moviemania.data.data_source.dto.movies_suggestions

import com.aditya.moviemania.data.data_source.dto.common.MovieDto
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val movie_count: Int,
    val movies: List<MovieDto>
)