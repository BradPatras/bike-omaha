package io.github.bradpatras.bikeomaha.data

import android.graphics.Color
import org.json.JSONArray
import org.json.JSONObject

data class Trail(
    val identifier: Long,
    val title: String,
    val geoJSON: JSONObject,
    val pathColor: Int = geoJSON.getPathColor(),
    var selected: Boolean = true
)

// Currently the layout properties of the routes are buried deep in the
// GeoJSON.  It might be a good idea to surface the relevant path properties
// as part of the top level trail info to make this less painful for the app.
private fun JSONObject.getPathColor(): Int {
    val features = this["features"] as? JSONArray
    val geometry = features?.get(0) as JSONObject
    val properties = geometry["properties"] as? JSONObject ?: return Color.CYAN
    val colorHex = properties["stroke"] as? String ?: return Color.CYAN
    return Color.parseColor(colorHex)
}