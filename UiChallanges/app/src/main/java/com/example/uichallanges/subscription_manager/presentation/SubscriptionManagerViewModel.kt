package com.example.uichallanges.subscription_manager.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SubscriptionManagerViewModel @Inject constructor(
): ViewModel() {

    private var _stateFlow = MutableStateFlow(SubscriptionManagerState())
    val stateFlow = _stateFlow.asStateFlow()

    fun onEvent(event: SubscriptionManagerEvent) {
        when(event){
            SubscriptionManagerEvent.OnMenuClicked -> {

            }
            SubscriptionManagerEvent.OnNotificationsClicked -> {

            }
            is SubscriptionManagerEvent.OnTabClicked ->
                _stateFlow.update { it.copy(screen = event.screen) }

            is SubscriptionManagerEvent.OnUpcomingPaymentClick -> {

            }
            SubscriptionManagerEvent.OnAddNewCategoryClicked -> {

            }
            is SubscriptionManagerEvent.OnSubCategorySelected -> {
                _stateFlow.update { it.copy(selectedCategory = event.subCategory) }
            }
            is SubscriptionManagerEvent.OnSubDetailsClicked -> {

            }
            SubscriptionManagerEvent.OnAddNewSubClicked -> {

            }
        }
    }
}