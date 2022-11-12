package com.matikano.tracker_presentation.search.components

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.matikano.core.R
import com.matikano.core_ui.theme.LocalSpacing
import com.matikano.tracker_presentation.search.SearchEvent

@Composable
fun SearchTopBar(
    mealName: String,
    onEvent: (SearchEvent) -> Unit,
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
                text = stringResource(
                    id = R.string.add_meal,
                    mealName
                ),
                color = MaterialTheme.colors.onSurface
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.navigate_back),
                tint = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .clickable {
                        onEvent(SearchEvent.OnNavigateBackClick)
                    }
            )
        }
    )
}