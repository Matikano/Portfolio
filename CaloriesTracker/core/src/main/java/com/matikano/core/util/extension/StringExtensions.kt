package com.matikano.core.util.extension

import java.util.*

fun String.capitalizeEnum(): String =
    lowercase().replaceFirstChar { firstChar ->
        if (firstChar.isLowerCase()) firstChar.titlecase(Locale.ROOT)
        else firstChar.toString()
    }