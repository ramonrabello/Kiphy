package com.github.ramonrabello.kiphy.trends.data.source.local

import com.github.ramonrabello.kiphy.trends.data.source.TrendingDataSource
import com.github.ramonrabello.kiphy.trends.model.TrendingResponse
import kotlinx.coroutines.Deferred

class LocalTrendingDataSource : TrendingDataSource {
    override fun loadTrendingAsync(): Deferred<TrendingResponse> {
        TODO("Not implemented")
    }

    //override fun loadTrendingAsync(): Deferred<TrendingResponse> = Deferred<>TrendingResponse((emptyList()))

}