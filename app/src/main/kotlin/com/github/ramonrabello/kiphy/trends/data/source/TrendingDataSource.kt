package com.github.ramonrabello.kiphy.trends.data.source

import com.github.ramonrabello.kiphy.trends.model.TrendingResponse
import kotlinx.coroutines.Deferred

interface TrendingDataSource {

    fun loadTrendingAsync(): Deferred<TrendingResponse>
}