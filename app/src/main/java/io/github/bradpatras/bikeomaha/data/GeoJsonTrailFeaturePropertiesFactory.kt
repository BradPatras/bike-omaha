package io.github.bradpatras.bikeomaha.data

import com.google.maps.android.data.geojson.GeoJsonFeature

class GeoJsonTrailFeaturePropertiesFactory {
    companion object {
        private const val kStroke = "stroke"
        private const val kStrokeOpacity = "stroke-opacity"
        private const val kStrokeWidth = "stroke-width"
        private const val kName = "name"

        private val default = GeoJsonTrailFeatureProperties(
            strokeColor = "#00000",
            strokeWidth = 5f,
            strokeOpacity = 1f,
            name = "Untitled Trail"
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

            return GeoJsonTrailFeatureProperties(strokeColor, strokeWidth, strokeOpacity, name)
        }
    }
}