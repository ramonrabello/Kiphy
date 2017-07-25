package com.github.ramonrabello.kiphy.data

import com.github.ramonrabello.kiphy.presentation.trends.TrendingEndpoint
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class that will handle access to Giphy API endpoints
 * in a fluent way.
 */
class GiphyApi {

    var retrofit: Retrofit
    val BASE_URL = "http://api.giphy.com"

    init {
        retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
    }

    fun trending(): TrendingEndpoint = retrofit.create(TrendingEndpoint::class.java)
    fun search(): TrendingEndpoint = retrofit.create(TrendingEndpoint::class.java)
}