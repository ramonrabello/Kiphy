package com.github.ramonrabello.kiphy.trends.data.source.remote

import com.github.ramonrabello.kiphy.trends.TrendingApi
import com.github.ramonrabello.kiphy.trends.data.source.TrendingDataSource

class RemoteTrendingDataSource(private val api: TrendingApi) : TrendingDataSource {

        override fun loadTrending() = api.loadTrending()

    }