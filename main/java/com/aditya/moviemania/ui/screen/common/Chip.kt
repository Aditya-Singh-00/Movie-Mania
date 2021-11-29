package com.aditya.moviemania.ui.screen.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aditya.moviemania.ui.theme.chipHeight
import com.aditya.moviemania.ui.theme.spaceSmall

@Composable
fun Chip(
    text: String
) {
    Box(
        modifier = Modifier
            .height(chipHeight)
            .padding(spaceSmall)
            .background(
                color = Color.Transparent
            )
            .border(
                border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                shape = RoundedCornerShape(chipHeight / 3)
            )
            .padding(spaceSmall)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.primary
        )
    }
}