package com.matikano.core_ui

import androidx.compose.animation.core.tween

const val BAR_ANIMATION_DURATION = 600

fun <T> basicBarAnimation(duration: Int = BAR_ANIMATION_DURATION) = tween<T>(
    durationMillis = duration
)