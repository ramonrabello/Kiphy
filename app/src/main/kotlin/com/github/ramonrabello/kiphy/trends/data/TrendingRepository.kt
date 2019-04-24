package com.github.ramonrabello.kiphy.trends.data

import com.github.ramonrabello.kiphy.trends.data.source.TrendingDataSource
import com.github.ramonrabello.kiphy.trends.model.TrendingResponse
import kotlinx.coroutines.Deferred

class TrendingRepository(
        private val remoteDataSource: TrendingDataSource) {

    fun loadTrendingAsync() : Deferred<TrendingResponse>
        //val local = localDataSource.loadTrendingAsync()
        = remoteDataSource.loadTrendingAsync()
}