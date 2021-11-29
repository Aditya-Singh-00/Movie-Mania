package com.aditya.moviemania.ui.screen.home

import com.aditya.moviemania.domain.model.Movie

data class MovieWithType(
    val movies: List<Movie>,
    val genre: String
)
