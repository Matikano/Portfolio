package com.example.uichallanges.subscription_manager.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uichallanges.subscription_manager.presentation.SubscriptionManagerEvent
import com.example.uichallanges.subscription_manager.presentation.SubscriptionManagerState
import com.example.uichallanges.subscription_manager.theme.DarkGrey
import com.example.uichallanges.ui.theme.overlappingOffset

@Composable
fun MySubsContent(
   state: SubscriptionManagerState,
   onEvent: (SubscriptionManagerEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrey)
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.categories) {subCategory ->
                RoundedSelectableButton(
                    text = subCategory.name,
                    onClick = {
                        onEvent(SubscriptionManagerEvent.OnSubCategorySelected(subCategory))
                    },
                    selected = state.selectedCategory == subCategory
                )
            }
            item {
                CircleButton(
                    icon = Icons.Default.Add,
                    onClick = {
                        onEvent(SubscriptionManagerEvent.OnAddNewCategoryClicked)
                    },
                    size = 36.dp
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(-overlappingOffset)
        ) {
            item {
                AddSubscriptionItem(
                    onClick = {
                        onEvent(SubscriptionManagerEvent.OnAddNewSubClicked)
                    }
                )
            }
            itemsIndexed(state.currentSubscriptions) {index, sub ->
                SubscriptionItem(
                    payment = sub,
                    onDetailsClick = {
                        onEvent(SubscriptionManagerEvent.OnSubDetailsClicked(sub))
                    },
                    isLast = index == state.currentSubscriptions.lastIndex,
                )
            }
        }
    }
}