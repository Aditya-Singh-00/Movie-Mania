package com.aditya.moviemania.data.data_source.dto.movies_details

import com.aditya.moviemania.data.data_source.dto.common.TorrentDto
import com.aditya.moviemania.domain.model.MovieDetail
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailDto(
    val background_image: String,
    val background_image_original: String,
    val cast: List<CastDto>? = null,
    val date_uploaded: String,
    val date_uploaded_unix: Int,
    val description_full: String,
    val description_intro: String,
    val download_count: Int,
    val genres: List<String>,
    val id: Int,
    val imdb_code: String,
    val language: String,
    val large_cover_image: String,
    val like_count: Int,
    val medium_cover_image: String,
    val medium_screenshot_image1: String? = null,
    val medium_screenshot_image2: String? = null,
    val medium_screenshot_image3: String? = null,
    val mpa_rating: String,
    val rating: Double,
    val runtime: Int,
    val slug: String,
    val small_cover_image: String,
    val title: String,
    val title_english: String,
    val title_long: String,
    val torrents: List<TorrentDto>,
    val url: String,
    val year: Int,
    val yt_trailer_code: String
) {
    fun toMovieDetail(): MovieDetail {
        return MovieDetail(
            backgroundImageOriginal = background_image_original,
            cast = cast?.map { it.toCast() } ?: emptyList(),
            descriptionFull = description_full,
            genres = genres,
            id = id,
            imdbCode = imdb_code,
            language = language,
            mediumCoverImage = medium_cover_image,
            mediumScreenshotImage1 = medium_screenshot_image1,
            mediumScreenshotImage2 = medium_screenshot_image2,
            mediumScreenshotImage3 = medium_screenshot_image3,
            mpaRating = mpa_rating,
            rating = rating,
            runtime = runtime,
            title = title_english,
            torrents = torrents.map { it.toTorrent() },
            url = url,
            year = year,
            ytTrailerCode = yt_trailer_code
        )
    }
}