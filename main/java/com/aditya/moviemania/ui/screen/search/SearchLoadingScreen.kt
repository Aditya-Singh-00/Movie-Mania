package com.aditya.moviemania.ui.screen.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.aditya.moviemania.ui.theme.cardHeightMedium
import com.aditya.moviemania.ui.theme.cardWidth
import com.aditya.moviemania.ui.theme.spaceSmall

@ExperimentalFoundationApi
@Composable
fun SearchLoadingScreen(
    brush: Brush
) {

    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(spaceSmall)
    ) {
        items(9) {
            Box(
                modifier = Modifier
                    .padding(spaceSmall)
                    .width(cardWidth)
                    .height(cardHeightMedium)
                    .background(brush)
            )
        }
    }

}