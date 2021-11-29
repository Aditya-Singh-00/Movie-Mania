package com.aditya.moviemania.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Torrent(
    val quality: String,
    val size: String,
    val type: String? = null,
    val url: String,
    val hash: String
)