package com.aditya.moviemania.ui.screen.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import com.aditya.moviemania.ui.theme.spaceSmall

@Composable
fun Ratings(
    rating: Double
) {

    val fixed = (rating / 2).toInt()
    val showHalf = (rating / 2) % fixed >= 0.25

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(spaceSmall),
        horizontalArrangement = Arrangement.SpaceEvenly,
        contentPadding = PaddingValues(spaceSmall)
    ) {
        items(fixed) {
            Image(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
            )
        }
        if (showHalf) {
            item {
                Image(
                    imageVector = Icons.Filled.StarHalf,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                )
            }
        }
    }
}