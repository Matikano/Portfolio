package com.matikano.tracker_presentation.tracker_overview.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.matikano.core_ui.LocalSpacing
import com.matikano.core_ui.TrackedFoodItem
import com.matikano.tracker_domain.model.TrackedFood
import com.matikano.core.R
import com.matikano.tracker_presentation.tracker_overview.TrackerOverviewEvent

@OptIn(ExperimentalCoilApi::class)
@Composable
fun TrackedFoodItem(
    trackedFood: TrackedFood,
    onEvent: (TrackerOverviewEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .padding(spacing.spaceExtraSmall)
            .shadow(
                elevation = TrackedFoodItem.shadowElevation,
                shape = MaterialTheme.shapes.small
            )
            .background(MaterialTheme.colors.surface)
            .padding(end = spacing.spaceMedium)
            .height(TrackedFoodItem.itemHeight),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
       Image(
           painter = rememberImagePainter(
               data = trackedFood.imageUrl,
               builder = {
                   crossfade(true)
                   error(R.drawable.ic_burger)
                   fallback(R.drawable.ic_burger)
               }
           ),
           contentDescription = trackedFood.name,
           contentScale = ContentScale.Crop,
           modifier = Modifier
               .fillMaxHeight()
               .aspectRatio(1f)
               .clip(
                   TrackedFoodItem.imageShape
               )
        )
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = trackedFood.name,
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                maxLines = TrackedFoodItem.itemNameMaxLines
            )
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Text(
                text = stringResource(
                    id = R.string.nutrient_info,
                    trackedFood.amount,
                    trackedFood.calories
                ),
                style = MaterialTheme.typography.body2
            )
        }
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = R.string.delete),
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable {
                        onEvent(TrackerOverviewEvent.OnDeleteTrackedFoodClick(trackedFood))
                    }
            )
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                NutrientInfo(
                    name = stringResource(id = R.string.protein),
                    amount = trackedFood.protein,
                    unit = stringResource(id = R.string.grams),
                    amountTextSize = TrackedFoodItem.nutrientInfoAmountTextSize,
                    unitTextSize = TrackedFoodItem.nutrientInfoUnitTextSize,
                    nameTextStyle = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                NutrientInfo(
                    name = stringResource(id = R.string.carbs),
                    amount = trackedFood.carbs,
                    unit = stringResource(id = R.string.grams),
                    amountTextSize = TrackedFoodItem.nutrientInfoAmountTextSize,
                    unitTextSize = TrackedFoodItem.nutrientInfoUnitTextSize,
                    nameTextStyle = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                NutrientInfo(
                    name = stringResource(id = R.string.fat),
                    amount = trackedFood.fat,
                    unit = stringResource(id = R.string.grams),
                    amountTextSize = TrackedFoodItem.nutrientInfoAmountTextSize,
                    unitTextSize = TrackedFoodItem.nutrientInfoUnitTextSize,
                    nameTextStyle = MaterialTheme.typography.body2
                )
            }
        }
    }
}