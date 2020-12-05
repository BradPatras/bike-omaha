package io.github.bradpatras.bikeomaha.data

import android.graphics.Color
import com.google.maps.android.data.geojson.GeoJsonFeature
import org.json.JSONArray
import org.json.JSONObject

data class Trail(
    val identifier: Long,
    val title: String,
    val geoJSON: JSONObject
)

fun Trail.getColor(): Int {
    val features = this.geoJSON["features"] as? JSONArray
    val geometry = features?.get(0) as JSONObject
    val properties = geometry["properties"] as? JSONObject ?: return Color.CYAN
    val colorHex = properties["stroke"] as? String ?: return Color.CYAN
    return Color.parseColor(colorHex)
}