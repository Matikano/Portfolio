package com.matikano.complimentapp.presentation.compliment.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matikano.complimentapp.domain.compliment.Compliment

@Composable
fun Compliment(
    modifier: Modifier = Modifier,
    compliment: Compliment
) {
    Column(
       modifier  = modifier
           .fillMaxSize()
           .verticalScroll(rememberScrollState())
           .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = compliment.content,
            fontSize = 32.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}