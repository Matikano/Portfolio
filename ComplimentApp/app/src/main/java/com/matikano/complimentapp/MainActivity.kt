package com.matikano.complimentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matikano.complimentapp.domain.compliment.Compliment
import com.matikano.complimentapp.presentation.compliment.ComplimentScreen
import com.matikano.complimentapp.presentation.compliment.ComplimentViewModel
import com.matikano.complimentapp.presentation.ui.theme.ComplimentAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ComplimentViewModel by viewModels()
    private var intentCompliment: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intentCompliment = intent.extras?.getString(EXTRA_KEY_COMPLIMENT)
        viewModel.loadCompliment(intentCompliment)

        setContent {
            ComplimentAppTheme {
                ComplimentScreen(viewModel = viewModel)
            }
        }
    }

    companion object {
        const val EXTRA_KEY_COMPLIMENT = "compliment"
    }
}

