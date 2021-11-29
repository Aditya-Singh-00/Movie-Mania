package com.aditya.moviemania.ui.screen.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import com.aditya.moviemania.domain.model.Movie
import com.aditya.moviemania.ui.theme.cardHeightMedium
import com.aditya.moviemania.ui.theme.spaceSmall

@Composable
fun PreviewCard(
    movie: Movie,
    modifier: Modifier = Modifier,
    largeCoverImage: String? = null,
    onClick: ((Int) -> Unit)? = null
) {
    Box(
        modifier = modifier
            .padding(spaceSmall)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(cardHeightMedium / 10)
            )
            .clickable {
                if (onClick != null) {
                    onClick(movie.id)
                }
            }
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(cardHeightMedium / 10)
        ) {
            Image(
                painter = rememberImagePainter(
                    data = largeCoverImage ?: movie.mediumCoverImage,
                    builder = { crossfade(300) }
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}