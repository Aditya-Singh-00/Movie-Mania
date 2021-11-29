package com.aditya.moviemania.ui.screen.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.moviemania.domain.model.Movie
import com.aditya.moviemania.domain.repository.MovieRepository
import com.aditya.moviemania.util.MovieManiaException
import com.aditya.moviemania.util.genres
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _homeScreenState: MutableState<HomeScreenState> =
        mutableStateOf(HomeScreenState.Loading)

    val homeScreenState: State<HomeScreenState> = _homeScreenState

    private val _movies: MutableState<MutableList<MovieWithType>> =
        mutableStateOf(mutableListOf())

    private val _latestMovies: MutableState<MutableList<Movie>> =
        mutableStateOf(mutableListOf())

    init {
        getLatestMovies()
        getAllMovies()
    }

    private fun getLatestMovies() {
        viewModelScope.launch {
            _latestMovies.value.addAll(repository.getMovies(limit = 6, genre = "Sci-Fi"))
        }
    }

    private fun getAllMovies() {
        viewModelScope.launch {
            genres.forEach { genre ->
                getMovies(genre)
            }
        }.invokeOnCompletion {
            _homeScreenState.value = HomeScreenState.Success(
                moviesWithType = _movies.value,
                latestMovies = _latestMovies.value
            )
        }
    }

    private suspend fun getMovies(genre: String) {
        try {
            val movieData = repository.getMovies(genre = genre)
            _movies.value.add(
                MovieWithType(
                    movies = movieData,
                    genre = genre
                )
            )
        } catch (e: MovieManiaException) {
            Log.e("HomeScreen", e.localizedMessage ?: "Something went wrong")
            _homeScreenState.value = HomeScreenState.Error(e.localizedMessage)
        }
    }
}