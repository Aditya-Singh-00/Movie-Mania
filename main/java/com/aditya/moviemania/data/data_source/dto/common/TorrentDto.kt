package com.aditya.moviemania.data.data_source.dto.common

import com.aditya.moviemania.domain.model.Torrent
import kotlinx.serialization.Serializable

@Serializable
data class TorrentDto(
    val date_uploaded: String,
    val date_uploaded_unix: Int,
    val hash: String,
    val peers: Int,
    val quality: String,
    val seeds: Int,
    val size: String,
    val type: String? = null,
    val url: String
) {
    fun toTorrent(): Torrent {
        return Torrent(
            quality = quality,
            size = size,
            type = type,
            url = url,
            hash = hash
        )
    }
}