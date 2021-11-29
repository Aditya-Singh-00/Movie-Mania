package com.aditya.moviemania.ui.screen.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import com.aditya.moviemania.ui.theme.searchBarHeight

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    onClick: (() -> Unit)? = null,
    hint: String,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    keyboardActions: KeyboardActions
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .clickable {
                if (onClick != null) {
                    onClick()
                }
            },
        enabled = enabled,
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface.copy(0.60f)
            )
        },
        leadingIcon = if (leadingIcon != null) {
            @Composable {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null
                )
            }
        } else {
            null
        },
        shape = RoundedCornerShape(searchBarHeight / 2),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = keyboardActions
    )
}