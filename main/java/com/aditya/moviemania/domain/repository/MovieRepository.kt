package com.aditya.moviemania.domain.repository

import com.aditya.moviemania.domain.model.Movie
import com.aditya.moviemania.domain.model.MovieDetail

interface MovieRepository {

    suspend fun getMovies(pageNo: Int = 1, limit: Int = 20, genre: String?= null) : List<Movie>

    suspend fun getMovieDetails(movieId: Int? = 10) : MovieDetail

    suspend fun getMovieSuggestions(movieId: Int? = 10) : List<Movie>

    suspend fun getSearchResults(query: String) : List<Movie>

}