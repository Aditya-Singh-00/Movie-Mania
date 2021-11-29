package com.aditya.moviemania.ui.screen.movie_detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.moviemania.domain.repository.MovieRepository
import com.aditya.moviemania.util.MovieManiaException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.URLEncoder
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movieDetailState: MutableState<MovieDetailState> =
        mutableStateOf(MovieDetailState.Loading)
    val movieDetailState: State<MovieDetailState> = _movieDetailState

    init {
        getMovieDetail()
    }

    fun watchMovieTrailer(youtubeId: String, context: Context) {
        val intentApp =
            Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$youtubeId"))
        val intentBrowser =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$youtubeId"))
        try {
            context.startActivity(intentApp)
        } catch (e: MovieManiaException) {
            context.startActivity(intentBrowser)
        }
    }

    fun downloadMovie(hash: String, name: String, context: Context) {
        val encodedName = URLEncoder.encode(name,"utf-8")
        val uri = Uri.parse(
            magnetPreUrl.plus("$hash&dn=$encodedName".plus(tracker))
        )
        val intent = Intent(Intent.ACTION_VIEW, uri)
        try {
            context.startActivity(intent)
        } catch (e: MovieManiaException) {
            Log.e("DetailScreen",e.localizedMessage?: "Something went wrong")
        }
    }

    private fun getMovieDetail() {
        viewModelScope.launch {
            try {
                _movieDetailState.value =
                    MovieDetailState.Success(
                        movieDetail = movieRepository.getMovieDetails(savedStateHandle.get("id")),
                        movieSuggestion = movieRepository.getMovieSuggestions(savedStateHandle.get("id"))
                    )
            } catch (e: MovieManiaException) {
                _movieDetailState.value =
                    MovieDetailState.Error(e.localizedMessage ?: "Something went wrong")
            }
        }
    }
}