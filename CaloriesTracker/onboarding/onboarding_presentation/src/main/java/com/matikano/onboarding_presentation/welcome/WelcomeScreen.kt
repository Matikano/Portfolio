package com.matikano.onboarding_presentation.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.matikano.core_ui.theme.LocalSpacing
import com.matikano.core.R
import com.matikano.core.util.UiEvent
import com.matikano.core_ui.navigation.Screen

@Composable
fun WelcomeScreen(
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(spacing.spaceMedium),
      
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = stringResource(id = R.string.welcome_text),
            style = MaterialTheme.typography.h1.copy(
                color = MaterialTheme.colors.onBackground
            ),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = {
                onNavigate(UiEvent.Navigate(Screen.Gender.navigationRoute))
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = stringResource(id = R.string.next).uppercase(),
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(spacing.spaceSmall)
            )
        }
    }
}