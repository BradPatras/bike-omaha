package io.github.bradpatras.bikeomaha.data

import com.google.maps.android.data.geojson.GeoJsonFeature

class GeoJsonTrailFeaturePropertiesFactory {
    companion object {
        private const val kStroke = "stroke"
        private const val kStrokeOpacity = "stroke-opacity"
        private const val kStrokeWidth = "stroke-width"
        private const val kName = "name"

        fun create(feature: GeoJsonFeature): GeoJsonTrailFeatureProperties? {
            if (!feature.hasProperty(kStroke)) return null
            if (!feature.hasProperty(kStrokeOpacity)) return null
            if (!feature.hasProperty(kStrokeWidth)) return null
            if (!feature.hasProperty(kName)) return null

            val strokeColor = feature.getProperty(kStroke)
            val strokeOpacity = feature.getProperty(kStrokeOpacity).toFloatOrNull() ?: return null
            val strokeWidth = feature.getProperty(kStrokeWidth).toFloatOrNull() ?: return null
            val name = feature.getProperty(kName)

            return GeoJsonTrailFeatureProperties(strokeColor, strokeWidth, strokeOpacity, name)
        }
    }
}