package com.github.ramonrabello.kiphy.common.ui.util

import android.content.Context
import androidx.core.content.ContextCompat
import com.github.ramonrabello.kiphy.R

/**
 * Utility that generates a colored placeholder
 * to be used by images.
 */
object ColoredPlaceholderGenerator {

    private val placeholderColors = arrayOf(
            R.color.colorLightGreen,
            R.color.colorLightBlue,
            R.color.colorLightPurple,
            R.color.colorLightRed,
            R.color.colorLightYellow
    )

    /**
     * Generates a random integer between 1..placeholderColors.size
     * (inclusive), which represents the resId for the placeholder color.
     */
    fun generate(context: Context): Int {
        val randomIndex = (1..placeholderColors.size).random()
        return ContextCompat.getColor(context, placeholderColors[randomIndex - 1])
    }
}