package com.aditya.moviemania.ui.screen.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.aditya.moviemania.domain.model.Movie
import com.aditya.moviemania.ui.theme.*

@Composable
fun MovieHorizontalRow(
    movies: List<Movie>,
    category: String? = null,
    onClick: ((Int) -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = spaceSmall)
    ) {
        category?.let {
            Text(
                text = category,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = spaceMedium, top = spaceMedium)
            )
        }
        LazyRow(
            contentPadding = PaddingValues(contentPadding)
        ) {
            items(items = movies) { movie ->
                PreviewCard(
                    movie = movie,
                    modifier = Modifier
                        .width(cardWidth)
                        .height(cardHeightMedium),
                    onClick = onClick
                )
            }
        }
    }

}