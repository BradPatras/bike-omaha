package io.github.bradpatras.bikeomaha.data

object TrailsCache {
    var trails: List<Trail>? = null

    fun clear() {
        trails = null
    }
}