package com.matikano.complimentapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.matikano.complimentapp.presentation.compliment.ComplimentEvent
import com.matikano.complimentapp.presentation.compliment.ComplimentScreen
import com.matikano.complimentapp.presentation.compliment.ComplimentViewModel
import com.matikano.complimentapp.presentation.ui.theme.ComplimentAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ComplimentViewModel by viewModels()
    private var intentCompliment: String? = null

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intentCompliment = intent.getStringExtra(EXTRA_KEY_COMPLIMENT)
        Log.d("NotificationService", "${this.javaClass.name} onNewIntent: content = $intentCompliment")
        viewModel.onEvent(ComplimentEvent.OnLoadCompliment(intentCompliment))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(intentCompliment == null){
            viewModel.onEvent(ComplimentEvent.OnLoadCompliment())
        }

        setContent {
            ComplimentAppTheme {
                ComplimentScreen(
                    viewModel = viewModel
                )
            }
        }
    }

    companion object {
        const val EXTRA_KEY_COMPLIMENT = "compliment"
    }
}

