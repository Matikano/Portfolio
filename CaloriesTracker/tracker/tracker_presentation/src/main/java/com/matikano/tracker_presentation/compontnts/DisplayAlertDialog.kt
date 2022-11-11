package com.matikano.tracker_presentation.compontnts

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.matikano.core.R
import com.matikano.core_ui.defaultBorderStroke
import kotlinx.coroutines.NonCancellable.cancel

@Composable
fun DisplayAlertDialog(
    title: String,
    message: String,
    isOpen: Boolean = false,
    onCancelClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    if(isOpen) {
        AlertDialog(
            onDismissRequest = {
                onCancelClick()
            },
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = message,
                    style = MaterialTheme.typography.subtitle1
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirmClick()
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.confirm),
                        style = MaterialTheme.typography.body1,
                    )
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        onCancelClick()
                    },
                    border = BorderStroke(
                        width = defaultBorderStroke,
                        color = MaterialTheme.colors.primary
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.primary
                    )
                }
            }
        )
    }
}