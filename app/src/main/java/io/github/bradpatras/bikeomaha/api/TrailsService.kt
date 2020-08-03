package io.github.bradpatras.bikeomaha.api

import androidx.lifecycle.LiveData
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.github.bradpatras.bikeomaha.api.adapters.JSONObjectAdapter
import io.github.bradpatras.bikeomaha.data.TrailsResponse
import okhttp3.HttpUrl
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.*

interface TrailsService {

    @GET("trails.json")
    fun trails(): Call<TrailsResponse>

    companion object {
        private val BASE_URL = HttpUrl.get("https://bradpatras.github.io/bike-omaha/v1/")

        fun create(baseUrl: HttpUrl = BASE_URL): TrailsService {
            val moshi = Moshi.Builder()
                .add(Date::class.java, Rfc3339DateJsonAdapter())
                .add(JSONObjectAdapter())
                .add(KotlinJsonAdapterFactory())
                .build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(TrailsService::class.java)
        }
    }
}