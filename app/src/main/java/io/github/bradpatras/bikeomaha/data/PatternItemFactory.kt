package io.github.bradpatras.bikeomaha.data

import com.google.android.gms.maps.model.Dot
import com.google.android.gms.maps.model.Gap
import com.google.android.gms.maps.model.PatternItem
import io.github.bradpatras.bikeomaha.data.LinePatternStyle.*

class PatternItemFactory {
    companion object {
        fun patternFromLinePatternStyle(style: LinePatternStyle): List<PatternItem> {
            return when (style) {
                DOTTED -> listOf(Dot(), Gap(4f))
                SOLID -> listOf()
            }
        }
    }
}