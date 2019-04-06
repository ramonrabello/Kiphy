package com.github.ramonrabello.kiphy.trends.data

import com.github.ramonrabello.kiphy.trends.TrendingApi
import com.github.ramonrabello.kiphy.trends.model.TrendingResponse
import io.reactivex.Single

interface TrendingDataSource {

    fun loadTrending(): Single<TrendingResponse>

    class Remote(private val api: TrendingApi) : TrendingDataSource {

        override fun loadTrending() =  api.loadTrending()

    }
}