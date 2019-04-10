package com.github.ramonrabello.kiphy.trends.data

import io.reactivex.Single

class TrendingRepository(
        private val localDataSource: TrendingDataSource,
        private val remoteDataSource: TrendingDataSource) {

    fun loadTrending() = Single.merge(
            remoteDataSource.loadTrending(),
            localDataSource.loadTrending())
}