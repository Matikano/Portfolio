package com.matikano.tracker_presentation.tracker_overview.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.*
import com.matikano.core_ui.theme.LocalSpacing
import com.matikano.core.R
import com.matikano.core_ui.theme.ExpandableMeal
import com.matikano.core_ui.components.UnitDisplay
import com.matikano.tracker_presentation.tracker_overview.Meal
import com.matikano.tracker_presentation.tracker_overview.TrackerOverviewEvent

@Composable
fun ExpandableMeal(
    meal: Meal,
    onEvent: (TrackerOverviewEvent) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onEvent(TrackerOverviewEvent.OnToggleMealClick(meal))
                }
                .padding(spacing.spaceMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = meal.drawableRes),
                contentDescription = meal.name.asString(context)
            )
            Spacer(modifier = Modifier.width(spacing.spaceMedium))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = meal.name.asString(context),
                        style = MaterialTheme.typography.h3,
                        color = MaterialTheme.colors.onBackground
                    )
                    Icon(
                        imageVector = if (meal.expanded) { 
                            Icons.Default.KeyboardArrowUp
                        } else Icons.Default.KeyboardArrowDown,
                        contentDescription = if (meal.expanded) {
                            stringResource(id = R.string.collapse)
                        } else  stringResource(id = R.string.extend),
                        tint = MaterialTheme.colors.onBackground
                    )
                }
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    UnitDisplay(
                        amount = meal.calories,
                        amountTextSize = ExpandableMeal.unitDisplayAmountTextSize,
                        unit = stringResource(id = R.string.kcal)
                    )
                    Row {
                        NutrientInfo(
                            name = stringResource(id = R.string.protein),
                            amount = meal.protein,
                            unit = stringResource(id = R.string.grams)
                        )
                        Spacer(modifier = Modifier.width(spacing.spaceSmall))
                        NutrientInfo(
                            name = stringResource(id = R.string.carbs),
                            amount = meal.carbs,
                            unit = stringResource(id = R.string.grams)
                        )
                        Spacer(modifier = Modifier.width(spacing.spaceSmall))
                        NutrientInfo(
                            name = stringResource(id = R.string.fat),
                            amount = meal.fat,
                            unit = stringResource(id = R.string.grams)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        AnimatedVisibility(visible = meal.expanded) {
            content()
        }
    }
}