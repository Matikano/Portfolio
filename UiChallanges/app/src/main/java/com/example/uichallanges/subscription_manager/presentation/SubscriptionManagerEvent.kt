package com.example.uichallanges.subscription_manager.presentation

import com.example.uichallanges.subscription_manager.model.Payment
import com.example.uichallanges.subscription_manager.model.Screen
import com.example.uichallanges.subscription_manager.model.SubCategory

sealed class SubscriptionManagerEvent {

    object OnMenuClicked: SubscriptionManagerEvent()
    object OnNotificationsClicked: SubscriptionManagerEvent()
    object OnAddNewCategoryClicked: SubscriptionManagerEvent()
    object OnAddNewSubClicked: SubscriptionManagerEvent()

    data class OnSubCategorySelected(val subCategory: SubCategory): SubscriptionManagerEvent()
    data class OnSubDetailsClicked(val payment: Payment): SubscriptionManagerEvent()
    data class OnTabClicked(val screen: Screen): SubscriptionManagerEvent()
    data class OnUpcomingPaymentClick(val payment: Payment): SubscriptionManagerEvent()
}