package com.aditya.moviemania.ui.screen.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import com.aditya.moviemania.ui.theme.bannerHeight
import com.aditya.moviemania.ui.theme.spaceMedium
import com.aditya.moviemania.ui.theme.spaceSmall

@Composable
fun TopBanner(
    name: String,
    rating: Double,
    background: String? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = rememberImagePainter(
                data = background,
                builder = { crossfade(true) }
            ),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(bannerHeight)
                .padding(spaceMedium)
                .weight(1f)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .height(bannerHeight)
                .padding(spaceSmall),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.h5
            )
            Ratings(rating = rating)
        }
    }
}