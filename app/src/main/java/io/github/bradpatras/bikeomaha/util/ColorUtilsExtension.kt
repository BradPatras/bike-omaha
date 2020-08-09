package io.github.bradpatras.bikeomaha.util

import android.graphics.Color
import androidx.core.graphics.ColorUtils
import java.lang.Integer.min
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

class ColorUtilsExt {
    companion object {
        fun colorWithAlpha(colorString: String, alpha: Float): Int {
            val alphaInt = (alpha * 255).roundToInt().absoluteValue
            val validAlpha = min(alphaInt, 255)
            val color = Color.parseColor(colorString)
            return ColorUtils.setAlphaComponent(color, validAlpha)
        }
    }
}

