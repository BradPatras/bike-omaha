package io.github.bradpatras.bikeomaha

import io.github.bradpatras.bikeomaha.api.TrailsService
import io.github.bradpatras.bikeomaha.data.TrailsResponse
import okhttp3.internal.wait
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertNotNull
import org.junit.Assert.fail
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrailsServiceTest {
//  This test not working, always gets a timeout from the mockwebserver
//    @Test
//    fun `trails service call populates TrailResponse`() {
//        val server = MockWebServer()
//
//        val body = MockResponseFileReader("trails.json").content
//        MockWebServer().enqueue(MockResponse().setBody(body))
//
//        server.start(0)
//        val service = TrailsService.create(server.url("/"))
//        val trailsResponse = service.trails()
//    }
}