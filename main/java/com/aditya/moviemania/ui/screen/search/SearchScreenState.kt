package com.aditya.moviemania.ui.screen.search

import com.aditya.moviemania.domain.model.Movie

sealed class SearchScreenState {
    data class Success(val movies: List<Movie>) : SearchScreenState()
    data class Error(val error: String? = null) : SearchScreenState()
    object Loading : SearchScreenState()
    object Passive : SearchScreenState()
}