package com.aditya.moviemania.data.repository

import com.aditya.moviemania.data.data_source.service.MovieService
import com.aditya.moviemania.domain.model.Movie
import com.aditya.moviemania.domain.model.MovieDetail
import com.aditya.moviemania.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRepository {

    override suspend fun getMovies(pageNo: Int, limit: Int, genre: String?): List<Movie> {
        val dtoMovies = movieService.getMovies(
            pageNo = pageNo,
            limit = limit,
            genre = genre
        )?.data?.movies ?: emptyList()
        return dtoMovies.map { it.toMovie() }
    }

    override suspend fun getMovieDetails(movieId: Int?): MovieDetail {
        val dtoMovie = movieService.getMovieDetails(movieId).data.movie
        return dtoMovie.toMovieDetail()
    }

    override suspend fun getMovieSuggestions(movieId: Int?): List<Movie> {
        val dtoMovies = movieService.getMovieSuggestion(movieId).data.movies
        return dtoMovies.map { it.toMovie() }
    }

    override suspend fun getSearchResults(query: String): List<Movie> {
        val dtoMovies = movieService.getSearchResults(query).data.movies
        return dtoMovies.map { it.toMovie() }
    }
}