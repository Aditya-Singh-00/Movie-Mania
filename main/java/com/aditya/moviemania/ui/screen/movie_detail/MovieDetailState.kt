package com.aditya.moviemania.ui.screen.movie_detail

import com.aditya.moviemania.domain.model.Movie
import com.aditya.moviemania.domain.model.MovieDetail

sealed class MovieDetailState {
    data class Success(
        val movieDetail: MovieDetail,
        val movieSuggestion: List<Movie>? = null
    ) : MovieDetailState()

    data class Error(val error: String? = null) : MovieDetailState()
    object Loading : MovieDetailState()
}