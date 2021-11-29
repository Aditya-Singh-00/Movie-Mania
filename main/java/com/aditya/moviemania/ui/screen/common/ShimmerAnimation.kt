package com.aditya.moviemania.ui.screen.common

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import com.aditya.moviemania.ui.theme.Gray
import com.aditya.moviemania.ui.theme.LightGray

@Composable
fun ShimmerAnimation(
    content: @Composable (Brush) -> Unit
) {

    val transition = rememberInfiniteTransition()
    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    val brush = Brush.linearGradient(
        colors = listOf(Gray, LightGray, Gray),
        start = Offset(10f,10f),
        end = Offset(translateAnimation, translateAnimation)
    )

    content(brush)
}