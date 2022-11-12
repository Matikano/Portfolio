package com.matikano.onboarding_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.matikano.core_ui.theme.LocalSpacing

@Composable
fun SelectableButton(
    text: String,
    isSelected: Boolean,
    color: Color,
    selectedColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector,
    textStyle: TextStyle = MaterialTheme.typography.button
) {
    val spacing = LocalSpacing.current
    
    Box(
        modifier = modifier
            .clip(CircleShape)
            .border(
                width = 2.dp,
                color = color,
                shape = CircleShape
            )
            .background(
                color = if (isSelected) color else Color.Transparent,
                shape = CircleShape
            )
            .clickable {
                onClick()
            }
            .padding(spacing.spaceMedium)
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint =  if (isSelected) selectedColor else color,
            )
            Spacer(modifier = Modifier.width(spacing.spaceSmall))
            Text(
                text = text,
                style = textStyle,
                color =  if (isSelected) selectedColor else color,
            )
        }
    }
}