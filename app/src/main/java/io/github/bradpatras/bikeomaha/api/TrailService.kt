package io.github.bradpatras.bikeomaha.api

import com.squareup.moshi.Moshi
import io.github.bradpatras.bikeomaha.data.Trail
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface TrailService {

    @GET("bike-omaha/trails.json")
    fun trails(): Call<List<Trail>>

    companion object {
        private const val BASE_URL = "https://bradpatras.github.io/bike-omaha/"

        fun create(): TrailService {
            val moshi = Moshi.Builder()
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