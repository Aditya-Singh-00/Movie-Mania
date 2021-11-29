package com.aditya.moviemania.ui.screen.common

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import com.aditya.moviemania.domain.model.Movie
import com.aditya.moviemania.ui.theme.cardHeightLarge
import com.aditya.moviemania.ui.theme.spaceMedium
import com.aditya.moviemania.ui.theme.spaceSmall
import kotlinx.coroutines.delay

@ExperimentalAnimationApi
@Composable
fun ChangingPreviewCard(
    movies: List<Movie>,
    onClick: (Int) -> Unit
) {

    val index = remember { mutableStateOf(0) }

    LaunchedEffect(key1 = index.value) {
        delay(3000)
        if (index.value == movies.size - 1) {
            index.value = 0
        } else {
            index.value++
        }
    }

    Box(
        modifier = Modifier
            .padding(spaceSmall)
            .fillMaxWidth()
    ) {
        AnimatedContent(
            targetState = index.value,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInHorizontally({ width -> width }) + fadeIn() with
                            slideOutHorizontally({ width -> -width }) + fadeOut()
                } else {
                    slideInHorizontally({ width -> -width }) + fadeIn() with
                            slideOutHorizontally({ width -> width }) + fadeOut()
                }.using(
                    SizeTransform(clip = false)
                )
            }
        ) { index ->
            PreviewCard(
                movie = movies[index],
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeightLarge),
                largeCoverImage = movies[index].largeCoverImage,
                onClick = onClick
            )
        }
        LazyRow(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(spaceMedium)
        ) {
            itemsIndexed(movies) { i, _ ->
                val vector = if (i == index.value) Icons.Filled.Circle else Icons.Outlined.Circle
                Image(
                    imageVector = vector,
                    contentDescription = null,
                    modifier = Modifier.size(spaceSmall),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary)
                )
                Spacer(modifier = Modifier.width(spaceSmall))
            }
        }
    }
}