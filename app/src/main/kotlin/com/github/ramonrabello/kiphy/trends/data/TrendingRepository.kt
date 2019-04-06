package com.github.ramonrabello.kiphy.trends.data

class TrendingRepository(private val remoteDataSource: TrendingDataSource) {

    fun loadTrends() = remoteDataSource.loadTrending()
}