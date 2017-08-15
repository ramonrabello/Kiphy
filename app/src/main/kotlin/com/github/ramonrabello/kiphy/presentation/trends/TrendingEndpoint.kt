package com.github.ramonrabello.kiphy.presentation.trends

import com.github.ramonrabello.kiphy.data.Trending
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Retrofit interface for Trending API endpoint.
 */
interface TrendingEndpoint {

    @Headers("api_key:{your_api_key_here}")
    @GET("/v1/gifs/trending")
    fun load(): Call<Trending>
}