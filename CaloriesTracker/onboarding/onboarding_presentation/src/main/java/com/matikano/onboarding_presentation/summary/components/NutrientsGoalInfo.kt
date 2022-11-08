package com.matikano.onboarding_presentation.summary.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.stringResource
import com.matikano.core.R
import com.matikano.core_ui.LocalSpacing

@Composable
fun NutrientsGoalInfo(
    modifier: Modifier = Modifier,
    carbsRatio: Float,
    proteinRatio: Float,
    fatRatio: Float,
) {
    val spacing = LocalSpacing.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(spacing.spaceSmall),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = stringResource(id = R.string.nutrient_goal),
            style = MaterialTheme.typography.h3,
        )
        Column(
            verticalArrangement = Arrangement.Top
        ) {
            Row {
                Text(
                    text = (carbsRatio * 100).toInt().toString(),
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier.alignBy(LastBaseline)
                )
                Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                Text(
                    text = stringResource(id = R.string.percent_carbs),
                    modifier = Modifier.alignBy(LastBaseline),
                )
            }
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Row {
                Text(
                    text = (proteinRatio * 100).toInt().toString(),
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier.alignBy(LastBaseline)
                )
                Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                Text(
                    text = stringResource(id = R.string.percent_proteins),
                    modifier = Modifier.alignBy(LastBaseline)
                )

            }
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Row {
                Text(
                    text = (fatRatio * 100).toInt().toString(),
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier.alignBy(LastBaseline)
                )
                Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                Text(
                    text = stringResource(id = R.string.percent_fats),
                    modifier = Modifier.alignBy(LastBaseline),
                )
            }
        }
    }
}