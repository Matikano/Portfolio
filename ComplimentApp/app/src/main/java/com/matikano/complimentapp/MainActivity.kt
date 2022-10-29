package com.matikano.complimentapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.matikano.complimentapp.navigation.Screens
import com.matikano.complimentapp.presentation.compliment.ComplimentEvent
import com.matikano.complimentapp.presentation.compliment.ComplimentScreen
import com.matikano.complimentapp.presentation.compliment.ComplimentViewModel
import com.matikano.complimentapp.presentation.ui.theme.ComplimentAppTheme
import com.matikano.complimentapp.util.extenstions.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ComplimentViewModel by viewModels()
    private var intentCompliment: String? = null

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intentCompliment = intent.getStringExtra(EXTRA_KEY_COMPLIMENT)
        viewModel.onEvent(ComplimentEvent.OnLoadCompliment(intentCompliment))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(intentCompliment == null){
            viewModel.onEvent(ComplimentEvent.OnLoadCompliment())
        }

        setContent {
            ComplimentAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screens.COMPLIMENT
                ){
                    composable(route = Screens.COMPLIMENT) {
                        ComplimentScreen(
                            viewModel = viewModel,
                            onNavigate = navController::navigate
                        )
                    }

                    composable(route = Screens.SETTINGS) {
                        Text(text = "SETTINGS")
                    }
                }

            }
        }
    }

    companion object {
        const val EXTRA_KEY_COMPLIMENT = "compliment"
    }
}

