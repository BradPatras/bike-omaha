package io.github.bradpatras.bikeomaha

import io.github.bradpatras.bikeomaha.api.TrailsService
import junit.framework.Assert.assertNotNull
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test

class TrailsServiceTest {

    @Test
    fun `trails service call populates TrailResponse`() {
        val server = MockWebServer()

        val body = MockResponseFileReader("trails.json").content
        MockWebServer().enqueue(MockResponse().setBody(body))

        server.start(0)

        val service = TrailsService.create(server.url("/"))
        val trailsResponse = service.trails().execute()

        assertNotNull(trailsResponse)

        server.shutdown()
    }
}