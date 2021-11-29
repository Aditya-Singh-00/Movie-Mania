package com.aditya.moviemania.ui.screen.common

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextOverflow
import com.aditya.moviemania.ui.theme.spaceSmall

@ExperimentalAnimationApi
@Composable
fun TextBox(
    heading: String,
    content: String? = null
) {

    val expanded = remember { mutableStateOf(false) }
    val rotationState = animateFloatAsState(
        if (expanded.value) 180f else 0f
    )

    val maxLines = if (expanded.value) Int.MAX_VALUE else 3

    content?.let {
        if (it.isNotBlank()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spaceSmall)
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = LinearOutSlowInEasing
                        )
                    )
                    .clickable {
                        expanded.value = !expanded.value
                    }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spaceSmall),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = heading,
                        style = MaterialTheme.typography.h5
                    )
                    IconButton(
                        onClick = { expanded.value = !expanded.value },
                        modifier = Modifier
                            .rotate(rotationState.value)
                            .padding(end = spaceSmall)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                }
                Text(
                    text = content,
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = maxLines,
                    modifier = Modifier
                        .padding(spaceSmall)
                )
            }
        }
    }
}