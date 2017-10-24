package com.github.ramonrabello.kiphy.presentation.trends

import com.github.ramonrabello.kiphy.data.Trending
import retrofit2.Call
import retrofit2.http.GET

/**
 * Retrofit interface for Trending API endpoint.
 */
interface TrendingEndpoint {

    @GET("/v1/gifs/trending")
    fun load(): Call<Trending>
}