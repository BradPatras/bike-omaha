package io.github.bradpatras.bikeomaha.data

import com.google.maps.android.data.geojson.GeoJsonFeature

class GeoJsonTrailFeaturePropertiesFactory {
    companion object {
        private const val kStroke = "stroke"
        private const val kStrokeOpacity = "stroke-opacity"
        private const val kStrokeWidth = "stroke-width"
        private const val kName = "name"
        private const val kPatternStyle = "pattern"

        private val default = GeoJsonTrailFeatureProperties(
            strokeColor = "#00000",
            strokeWidth = 5f,
            strokeOpacity = 1f,
            name = "Untitled Trail",
            patternStyle = LinePatternStyle.SOLID
        )

        fun create(feature: GeoJsonFeature): GeoJsonTrailFeatureProperties {
            val strokeColor: String = if (feature.hasProperty(kStroke)) {
                feature.getProperty(kStroke)
            } else {
                default.strokeColor
            }

            val strokeOpacity: Float = if (feature.hasProperty(kStrokeOpacity)) {
                feature.getProperty(kStrokeOpacity).toFloatOrNull() ?: default.strokeOpacity
            } else {
                default.strokeOpacity
            }

            val strokeWidth: Float = if (feature.hasProperty(kStrokeWidth)) {
                feature.getProperty(kStrokeWidth).toFloatOrNull() ?: default.strokeWidth
            } else {
                default.strokeWidth
            }

            val name: String = if (feature.hasProperty(kName)) {
                feature.getProperty(kName)
            } else {
                default.name
            }

            val patternStyle: LinePatternStyle = if (feature.hasProperty(kPatternStyle)) {
                LinePatternStyle.valueOf(feature.getProperty(kPatternStyle))
            } else {
                default.patternStyle
            }

            return GeoJsonTrailFeatureProperties(strokeColor, strokeWidth, strokeOpacity, name, patternStyle)
        }
    }
}