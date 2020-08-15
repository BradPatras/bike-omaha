package io.github.bradpatras.bikeomaha.util

import com.google.maps.android.data.geojson.GeoJsonFeature
import com.google.maps.android.data.geojson.GeoJsonLineStringStyle
import io.github.bradpatras.bikeomaha.data.GeoJsonTrailFeaturePropertiesFactory
import io.github.bradpatras.bikeomaha.data.PatternItemFactory

fun GeoJsonFeature.applyPropertyStyles() {
    val properties = GeoJsonTrailFeaturePropertiesFactory.create(this)
    lineStringStyle = GeoJsonLineStringStyle().apply {
        color = ColorUtilsExt.colorWithAlpha(properties.strokeColor, properties.strokeOpacity)
        width = properties.strokeWidth
        pattern = PatternItemFactory.patternFromLinePatternStyle(properties.patternStyle)
    }
}