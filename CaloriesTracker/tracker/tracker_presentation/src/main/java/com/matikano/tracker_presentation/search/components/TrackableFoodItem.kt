package com.matikano.tracker_presentation.search.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.matikano.core_ui.theme.LocalSpacing
import com.matikano.core.R
import com.matikano.core_ui.theme.TrackableFoodItem
import com.matikano.tracker_domain.model.MealType
import com.matikano.tracker_presentation.search.SearchEvent
import com.matikano.tracker_presentation.search.TrackableFoodUiState
import com.matikano.tracker_presentation.tracker_overview.components.NutrientInfo
import java.time.LocalDate

@OptIn(ExperimentalCoilApi::class, ExperimentalComposeUiApi::class)
@Composable
fun TrackableFoodItem(
    trackableFoodUiState: TrackableFoodUiState,
    modifier: Modifier = Modifier,
    date: LocalDate,
    mealType: MealType,
    onEvent: (SearchEvent) -> Unit,
) {
    val food = trackableFoodUiState.food
    val spacing = LocalSpacing.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .padding(spacing.spaceExtraSmall)
            .shadow(
                elevation = TrackableFoodItem.shadowElevation,
                shape = MaterialTheme.shapes.small
            )
            .background(MaterialTheme.colors.surface)
            .clickable { onEvent(SearchEvent.OnToggleTrackableFood(food)) }
            .padding(end = spacing.spaceMedium)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(1f),
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = food.imageUrl,
                        builder = {
                            crossfade(true)
                            error(R.drawable.ic_burger)
                            fallback(R.drawable.ic_burger)
                        }
                    ),
                    contentDescription = food.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(TrackableFoodItem.imageSize)
                        .clip(TrackableFoodItem.imageClip)
                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                Column(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = food.name,
                        style = MaterialTheme.typography.body1,
                        maxLines = TrackableFoodItem.nameMaxLines,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(spacing.spaceSmall))
                    Text(
                        text = stringResource(
                            id = R.string.kcal_per_100g,
                            food.caloriesPer100g
                        ),
                        style = MaterialTheme.typography.body2
                    )
                }
            }
            Row {
                NutrientInfo(
                    name = stringResource(id = R.string.protein),
                    amount = food.proteinPer100g,
                    unit = stringResource(id = R.string.grams),
                    amountTextSize = TrackableFoodItem.nutrientInfoAmountTextSize,
                    unitTextSize = TrackableFoodItem.nutrientInfoUnitTextSize,
                    nameTextStyle = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.width(spacing.spaceSmall))
                NutrientInfo(
                    name = stringResource(id = R.string.carbs),
                    amount = food.carbsPer100g,
                    unit = stringResource(id = R.string.grams),
                    amountTextSize = TrackableFoodItem.nutrientInfoAmountTextSize,
                    unitTextSize = TrackableFoodItem.nutrientInfoUnitTextSize,
                    nameTextStyle = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.width(spacing.spaceSmall))
                NutrientInfo(
                    name = stringResource(id = R.string.fat),
                    amount = food.fatPer100g,
                    unit = stringResource(id = R.string.grams),
                    amountTextSize = TrackableFoodItem.nutrientInfoAmountTextSize,
                    unitTextSize = TrackableFoodItem.nutrientInfoUnitTextSize,
                    nameTextStyle = MaterialTheme.typography.body2
                )
            }
        }
        AnimatedVisibility(visible = trackableFoodUiState.isExpanded) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.spaceMedium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    BasicTextField(
                        value = trackableFoodUiState.amount,
                        onValueChange = { amount ->
                            onEvent(SearchEvent.OnAmountForFoodChange(amount, food))
                        },
                        textStyle =  TextStyle.Default.copy(
                            color = MaterialTheme.colors.onBackground
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = if(trackableFoodUiState.amount.isNotBlank()) {
                                ImeAction.Done
                            } else ImeAction.Default,
                            keyboardType = KeyboardType.Number
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                onEvent(
                                    SearchEvent.OnTrackFoodClick(
                                        food = food,
                                        date = date,
                                        mealType = mealType
                                    )
                                )
                                keyboardController?.hide()
                            }
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .border(
                                shape = MaterialTheme.shapes.small,
                                width = TrackableFoodItem.amountTextFieldBorder,
                                color = MaterialTheme.colors.onSurface
                            )
                            .alignBy(LastBaseline)
                            .padding(spacing.spaceMedium)
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                    Text(
                        text = stringResource(id = R.string.grams),
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.alignBy(LastBaseline)
                    )
                }
                IconButton(
                    onClick = {
                        onEvent(
                            SearchEvent.OnTrackFoodClick(
                                food = food,
                                mealType = mealType,
                                date = date
                            )
                        )
                        keyboardController?.hide()
                    },
                    enabled = trackableFoodUiState.amount.isNotBlank()
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.track))
                }
            }
        }

    }
}