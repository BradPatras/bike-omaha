package io.github.bradpatras.bikeomaha.data

import org.json.JSONObject

data class Trail(
    val identifier: Long,
    val title: String,
    val geoJSON: JSONObject
)