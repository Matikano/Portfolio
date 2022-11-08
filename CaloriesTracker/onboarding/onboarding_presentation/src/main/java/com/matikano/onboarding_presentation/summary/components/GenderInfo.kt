package com.matikano.onboarding_presentation.summary.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Transgender
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.matikano.core.R
import com.matikano.core.domain.model.Gender
import com.matikano.core.util.UiText
import java.util.Locale


@Composable
fun GenderInfo(
    modifier: Modifier = Modifier,
    gender: Gender
) {
    InfoRow(
        modifier = modifier,
        title = UiText.StringResource(R.string.gender),
        info = gender.name.lowercase().replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.ROOT)
            else it.toString()
        },
    )
}