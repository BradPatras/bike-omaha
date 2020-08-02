package io.github.bradpatras.bikeomaha.data

import java.util.*

data class TrailsResponse(val lastUpdated: Date,
                          val trails: List<Trail>)