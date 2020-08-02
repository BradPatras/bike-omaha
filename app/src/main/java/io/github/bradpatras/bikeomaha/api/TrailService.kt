package io.github.bradpatras.bikeomaha.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import io.github.bradpatras.bikeomaha.api.adapters.JSONObjectAdapter
import io.github.bradpatras.bikeomaha.data.Trail
import io.github.bradpatras.bikeomaha.data.TrailsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.*

interface TrailService {

    @GET("trails.json")
    fun trails(): Call<TrailsResponse>

    companion object {
        private const val BASE_URL = "https://bradpatras.github.io/bike-omaha/v1/"

        fun create(): TrailService {
            val moshi = Moshi.Builder()
                .add(Date::class.java, Rfc3339DateJsonAdapter())
                .add(JSONObjectAdapter::class.java)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(TrailService::class.java)
        }
    }
}