package com.aditya.moviemania.ui.screen.home

import com.aditya.moviemania.domain.model.Movie

sealed class HomeScreenState {
    data class Success(
        val moviesWithType: List<MovieWithType>,
        val latestMovies: List<Movie>? = null
    ) : HomeScreenState()
    data class Error(val error: String? = null) : HomeScreenState()
    object Loading : HomeScreenState()

}
