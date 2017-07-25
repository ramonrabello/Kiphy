package com.github.ramonrabello.kiphy.presentation.trends

import com.github.ramonrabello.kiphy.data.Trending
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Created by ramonrabello on 03/07/17.
 */
interface TrendingEndpoint {

    @Headers("api_key:53bb0d47f8af4574aa50807c5516a3ef")
    @GET("/v1/gifs/trending")
    fun loadTrends(): Call<Trending>
}