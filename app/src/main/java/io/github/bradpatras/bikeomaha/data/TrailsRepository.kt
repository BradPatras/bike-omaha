package io.github.bradpatras.bikeomaha.data

import io.github.bradpatras.bikeomaha.api.TrailsService

class TrailsRepository {
    suspend fun getTrails() = TrailsService.create().trails()
}