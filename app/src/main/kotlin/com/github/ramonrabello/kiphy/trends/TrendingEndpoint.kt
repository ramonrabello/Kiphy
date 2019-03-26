package com.github.ramonrabello.kiphy.trends

import com.github.ramonrabello.kiphy.trends.model.TrendingResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Retrofit interface for Trending API endpoint.
 */
interface TrendingEndpoint {

    @GET("/v1/gifs/trending")
    fun load(): Call<TrendingResponse>
}