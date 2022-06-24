package com.gmail.matikano9.mobilebankappui.utils

object Extensions {
    fun Double.format(digits: Int) = "%.${digits}f".format(this)
}