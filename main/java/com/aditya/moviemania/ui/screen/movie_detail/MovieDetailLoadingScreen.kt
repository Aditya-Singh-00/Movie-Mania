package com.aditya.moviemania.ui.screen.movie_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.aditya.moviemania.ui.theme.*

@Composable
fun MovieDetailLoadingScreen(
    brush: Brush
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spaceMedium)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeightLarge)
                .background(
                    color = Color.Transparent
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.45f)
                    .height(cardHeightMedium)
                    .padding(start = spaceMedium)
                    .align(Alignment.CenterStart)
                    .background(brush = brush)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(spaceLarge),
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.35f)
                        .height(spaceLarge)
                        .background(brush = brush)
                )
                Spacer(modifier = Modifier.height(spaceMedium))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.17f)
                        .height(spaceLarge)
                        .background(brush)
                )
            }
        }
        Spacer(modifier = Modifier.height(spaceLarge))
        Box(
            modifier = Modifier
                .padding(horizontal = spaceMedium, vertical = spaceSmall)
                .fillMaxWidth()
                .height(spaceLarge)
                .background(brush)
        )
        Spacer(modifier = Modifier.height(spaceLarge))
        Box(
            modifier = Modifier
                .padding(horizontal = spaceMedium, vertical = spaceSmall)
                .fillMaxWidth(0.50f)
                .height(spaceLarge)
                .background(brush)
        )
        Spacer(modifier = Modifier.height(spaceLarge))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(spaceLarge * 4f)
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = spaceMedium, vertical = spaceSmall)
                    .fillMaxWidth()
                    .height(spaceLarge)
                    .background(brush)
            )
            Box(
                modifier = Modifier
                    .padding(horizontal = spaceMedium, vertical = spaceSmall)
                    .fillMaxWidth()
                    .height(spaceLarge)
                    .background(brush)
            )
            Box(
                modifier = Modifier
                    .padding(horizontal = spaceMedium, vertical = spaceSmall)
                    .fillMaxWidth(0.40f)
                    .height(spaceLarge)
                    .background(brush)
            )
        }
        Spacer(modifier = Modifier.height(spaceLarge))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(3) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .padding(spaceSmall)
                            .clip(CircleShape)
                            .background(
                                brush = brush,
                                shape = CircleShape
                            )
                            .size(spaceLarge * 2f)

                    )
                    Box(
                        modifier = Modifier
                            .padding(spaceSmall)
                            .width(cardWidth * 0.7f)
                            .height(spaceLarge)
                            .background(brush)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(spaceMedium))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(spaceLarge * 4f)
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = spaceMedium, vertical = spaceSmall)
                    .fillMaxWidth()
                    .height(spaceLarge)
                    .background(brush)
            )
            Box(
                modifier = Modifier
                    .padding(horizontal = spaceMedium, vertical = spaceSmall)
                    .fillMaxWidth()
                    .height(spaceLarge)
                    .background(brush)
            )
        }
    }
}