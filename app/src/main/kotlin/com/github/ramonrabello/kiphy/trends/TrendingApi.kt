package com.github.ramonrabello.kiphy.trends

import com.github.ramonrabello.kiphy.trends.model.TrendingResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 * Retrofit interface for Trending API endpoint.
 */
interface TrendingApi {

    @GET("/v1/gifs/trending")
    fun loadTrendingAsync(): Deferred<TrendingResponse>
}