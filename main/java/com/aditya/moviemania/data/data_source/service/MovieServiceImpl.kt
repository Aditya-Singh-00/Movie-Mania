package com.aditya.moviemania.data.data_source.service

import android.util.Log
import com.aditya.moviemania.data.data_source.dto.movie_search.MovieSearchResponse
import com.aditya.moviemania.data.data_source.dto.movies.MovieResponse
import com.aditya.moviemania.data.data_source.dto.movies_details.MovieDetailResponse
import com.aditya.moviemania.data.data_source.dto.movies_suggestions.MovieSuggestionResponse
import com.aditya.moviemania.data.util.HttpRoutes
import com.aditya.moviemania.util.MovieManiaException
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class MovieServiceImpl(
    private val client: HttpClient
) : MovieService {

    override suspend fun getMovies(
        pageNo: Int,
        limit: Int,
        genre: String?
    ): MovieResponse {
        Log.e("Movie Service", "Inside get Movies")
        return try {
            client.get {
                url(HttpRoutes.GET_MOVIES) {
                    parameter("page", pageNo)
                    parameter("limit", limit)
                    genre?.let {
                        parameter("genre", genre)
                    }
                    parameter("sort_by","year")
                    parameter("order_by","desc")
                }
            }
        } catch (e: ClientRequestException) {
            throw MovieManiaException(e.response.status.description)
        } catch (e: ServerResponseException) {
            throw MovieManiaException(e.response.status.description)
        } catch (e: RedirectResponseException) {
            throw MovieManiaException(e.response.status.description)
        } catch (e: Exception) {
            throw MovieManiaException(e.localizedMessage ?: "Something went wrong")
        }
    }

    override suspend fun getMovieDetails(movieId: Int?): MovieDetailResponse {
        return try {
            client.get {
                url(HttpRoutes.GET_MOVIE_DETAILS) {
                    parameter("movie_id", movieId)
                    parameter("with_images", "true")
                    parameter("with_cast", "true")
                }
            }
        } catch (e: ClientRequestException) {
            throw MovieManiaException(e.response.status.description)
        } catch (e: ServerResponseException) {
            throw MovieManiaException(e.response.status.description)
        } catch (e: RedirectResponseException) {
            throw MovieManiaException(e.response.status.description)
        } catch (e: Exception) {
            throw MovieManiaException(e.localizedMessage ?: "Something went wrong")
        }
    }

    override suspend fun getMovieSuggestion(movieId: Int?): MovieSuggestionResponse {
        return try {
            client.get {
                url(HttpRoutes.GET_MOVIE_SUGGESTIONS) {
                    parameter("movie_id", movieId)
                }
            }
        } catch (e: ClientRequestException) {
            throw MovieManiaException(e.response.status.description)
        } catch (e: ServerResponseException) {
            throw MovieManiaException(e.response.status.description)
        } catch (e: RedirectResponseException) {
            throw MovieManiaException(e.response.status.description)
        } catch (e: Exception) {
            throw MovieManiaException(e.localizedMessage ?: "Something went wrong")
        }
    }

    override suspend fun getSearchResults(query: String): MovieSearchResponse {
        return try {
            client.get {
                url(HttpRoutes.GET_MOVIES) {
                    parameter("query_term",query)
                    parameter("sort_by","year")
                    parameter("order_by","desc")
                }
            }
        } catch (e: ClientRequestException) {
            throw MovieManiaException(e.response.status.description)
        } catch (e: ServerResponseException) {
            throw MovieManiaException(e.response.status.description)
        } catch (e: RedirectResponseException) {
            throw MovieManiaException(e.response.status.description)
        } catch (e: Exception) {
            throw MovieManiaException(e.localizedMessage ?: "Something went wrong")
        }
    }
}