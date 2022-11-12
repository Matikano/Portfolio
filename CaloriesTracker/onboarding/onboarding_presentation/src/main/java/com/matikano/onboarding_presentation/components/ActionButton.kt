package com.matikano.onboarding_presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.matikano.core_ui.theme.LocalSpacing

@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.button
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        shape = CircleShape
    ) {
        Text(
            text = text,
            style = textStyle,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(LocalSpacing.current.spaceMedium)
        )
    }
}