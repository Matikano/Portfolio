package com.gmail.matikano9.todoapp.presentation.splash

import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Task
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gmail.matikano9.todoapp.presentation.destinations.ToDoListScreenDestination
import com.gmail.matikano9.todoapp.presentation.todo_list.ToDoListScreen
import com.gmail.matikano9.todoapp.util.Constants.Splash.SPLASH_DURATION
import com.gmail.matikano9.todoapp.R
import com.gmail.matikano9.todoapp.presentation.destinations.SplashScreenDestination
import com.gmail.matikano9.todoapp.presentation.ui.theme.SPACE_MEDIUM
import com.gmail.matikano9.todoapp.presentation.ui.theme.SPLASH_SCREEN_ICON_SIZE
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator,
) {
   LaunchedEffect(key1 = true){
       delay(SPLASH_DURATION)
       navigator.navigate(ToDoListScreenDestination()){
           popUpTo(SplashScreenDestination().route){
               inclusive = true
           }
       }
   }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(SPLASH_SCREEN_ICON_SIZE),
                imageVector = Icons.Outlined.Task,
                contentDescription = stringResource(id = R.string.task_icon),
                tint = MaterialTheme.colors.onBackground,
            )
            Spacer(modifier = Modifier.height(SPACE_MEDIUM))
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colors.onBackground
            )
        }
    }


}