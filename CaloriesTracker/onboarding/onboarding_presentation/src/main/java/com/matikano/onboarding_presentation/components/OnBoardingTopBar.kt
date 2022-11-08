package com.matikano.onboarding_presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.matikano.core.util.UiEvent
import com.matikano.core_ui.LocalSpacing

@Composable
fun OnBoardingTopBar(
    onNavigateBackClicked: () -> Unit,
    title: String
) {
    val spacing = LocalSpacing.current

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.spaceSmall),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.onSurface
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "navigate back",
                tint = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .clickable {
                        onNavigateBackClicked()
                    }
            )
        }
    )
}