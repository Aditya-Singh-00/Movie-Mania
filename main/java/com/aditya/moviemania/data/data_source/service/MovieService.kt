package com.aditya.moviemania.data.data_source.service

import com.aditya.moviemania.data.data_source.dto.movie_search.MovieSearchResponse
import com.aditya.moviemania.data.data_source.dto.movies.MovieResponse
import com.aditya.moviemania.data.data_source.dto.movies_details.MovieDetailResponse
import com.aditya.moviemania.data.data_source.dto.movies_suggestions.MovieSuggestionResponse

interface MovieService {

    suspend fun getMovies(pageNo: Int, limit: Int, genre: String?): MovieResponse?

    suspend fun getMovieDetails(movieId: Int?): MovieDetailResponse

    suspend fun getMovieSuggestion(movieId: Int?): MovieSuggestionResponse

    suspend fun getSearchResults(query: String): MovieSearchResponse

}