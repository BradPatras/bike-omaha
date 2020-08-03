package io.github.bradpatras.bikeomaha

import io.github.bradpatras.bikeomaha.api.TrailsService

class TrailsResponseTest {
    val trailsService = TrailsService.create()
    val t = trailsService.trails().Response
}