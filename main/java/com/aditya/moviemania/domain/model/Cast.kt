package com.aditya.moviemania.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Cast(
    val characterName: String,
    val imdbCode: String,
    val name: String,
    val urlSmallImage: String?
)