package com.matikano.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.matikano.core.R
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.LastBaseline
import com.matikano.core_ui.components.UnitDisplay
import com.matikano.core_ui.theme.*
import com.matikano.tracker_presentation.tracker_overview.TrackerOverviewEvent
import com.matikano.tracker_presentation.tracker_overview.TrackerOverviewState

@Composable
fun NutrientsHeader(
    state: TrackerOverviewState,
    modifier: Modifier = Modifier,
    onEvent: (TrackerOverviewEvent) -> Unit
) {
    val spacing = LocalSpacing.current
    val animatedCalorieCount = animateIntAsState(
        targetValue = state.totalCalories,
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(NutrientsHeader.shape)
            .background(MaterialTheme.colors.primary)
            .padding(
                top = spacing.spaceLarge,
                start = spacing.spaceLarge,
                end = spacing.spaceLarge,
                bottom = spacing.spaceExtraLarge,
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            UnitDisplay(
                amount = animatedCalorieCount.value,
                unit = stringResource(id = R.string.kcal),
                amountColor = MaterialTheme.colors.onPrimary,
                amountTextSize = NutrientsHeader.unitDisplayAmountTextSize,
                unitTextColor = MaterialTheme.colors.onPrimary,
                modifier = Modifier.align(Alignment.Bottom)
            )
            Column {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = stringResource(id = R.string.settings),
                        tint = MaterialTheme.colors.onPrimary,
                        modifier = Modifier
                            .size(NutrientsHeader.infoIconSize)
                            .clip(circular)
                            .clickable {
                                onEvent(TrackerOverviewEvent.OnInfoButtonClick)
                            }
                            .alignBy(LastBaseline)
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                    Text(
                        text = stringResource(id = R.string.your_goal),
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.alignBy(LastBaseline)
                    )
                }
                UnitDisplay(
                    amount = state.caloriesGoal,
                    unit = stringResource(id = R.string.kcal),
                    amountColor = MaterialTheme.colors.onPrimary,
                    amountTextSize = NutrientsHeader.unitDisplayAmountTextSize,
                    unitTextColor = MaterialTheme.colors.onPrimary,
                )
            }
        }
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        NutrientsBar(
            carbs = state.totalCarbs,
            protein = state.totalProtein,
            fat = state.totalFat,
            calories = state.totalCalories,
            caloriesGoal = state.caloriesGoal,
            modifier = Modifier
                .fillMaxWidth()
                .height(NutrientsHeader.nutrientsBarHeight)
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NutrientBarInfo(
                value = state.totalProtein,
                goal = state.proteinGoal,
                name = stringResource(id = R.string.protein),
                color = ProteinColor,
                modifier = Modifier.size(NutrientsHeader.nutrientBarInfoSize)
            )
            NutrientBarInfo(
                value = state.totalCarbs,
                goal = state.carbsGoal,
                name = stringResource(id = R.string.carbs),
                color = CarbsColor,
                modifier = Modifier.size(NutrientsHeader.nutrientBarInfoSize)
            )
            NutrientBarInfo(
                value = state.totalFat,
                goal = state.fatGoal,
                name = stringResource(id = R.string.fat),
                color = FatColor,
                modifier = Modifier.size(NutrientsHeader.nutrientBarInfoSize)
            )
        }
        
    }
}