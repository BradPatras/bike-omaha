package io.github.bradpatras.bikeomaha.data

import io.github.bradpatras.bikeomaha.api.TrailsService

class TrailsRepository {
    suspend fun getTrails(): List<Trail> {
        return TrailsCache.trails ?: TrailsService.create().trails().trails.also { TrailsCache.trails = it }
    }
}