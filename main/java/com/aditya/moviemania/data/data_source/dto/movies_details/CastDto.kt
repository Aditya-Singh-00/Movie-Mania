package com.aditya.moviemania.data.data_source.dto.movies_details

import com.aditya.moviemania.domain.model.Cast
import kotlinx.serialization.Serializable

@Serializable
data class CastDto(
    val character_name: String,
    val imdb_code: String,
    val name: String,
    val url_small_image: String? = null
) {
    fun toCast(): Cast {
        return Cast(
            characterName = character_name,
            imdbCode = imdb_code,
            name = name,
            urlSmallImage = url_small_image
        )
    }
}