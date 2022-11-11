package com.matikano.tracker_presentation.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import com.matikano.core.R
import com.matikano.core_ui.LocalSpacing
import com.matikano.core_ui.SearchTextField
import com.matikano.tracker_presentation.search.SearchEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextField(
    text: String,
    onEvent: (SearchEvent) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = stringResource(id = R.string.search),
    shouldShowHint: Boolean = true,
) {
    val spacing = LocalSpacing.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = { value ->
                onEvent(SearchEvent.OnQueryChange(value))
            },
            singleLine = true,
            keyboardActions = KeyboardActions (
                onSearch = {
                    onEvent(SearchEvent.OnSearch)
                    defaultKeyboardAction(ImeAction.Search)
                    keyboardController?.hide()
                }
            ),
            keyboardOptions = KeyboardOptions(
              imeAction = ImeAction.Search
            ),
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .padding(spacing.shadowPadding)
                .shadow(
                    elevation = SearchTextField.shadowElevation,
                    shape = MaterialTheme.shapes.small
                )
                .background(MaterialTheme.colors.surface)
                .fillMaxWidth()
                .padding(spacing.spaceMedium)
                .padding(end = spacing.spaceMedium)
                .onFocusChanged { focusState ->
                    onEvent(SearchEvent.OnSearchFocusChange(focusState.isFocused))
                }
        )
        if (shouldShowHint) {
            Text(
                text = hint,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Light,
                color = Color.LightGray,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = spacing.spaceMedium)
            )
        }
        IconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = {
                onEvent(SearchEvent.OnSearch)
                keyboardController?.hide()
            }
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search)
            )
        }
    }
}