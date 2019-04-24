package com.github.ramonrabello.kiphy.trends.data

import com.github.ramonrabello.kiphy.trends.data.source.TrendingDataSource
import io.reactivex.Single

class TrendingRepository(
        private val localDataSource: TrendingDataSource,
        private val remoteDataSource: TrendingDataSource) {

    fun loadTrending() = Single.merge(
            remoteDataSource.loadTrending(),
            localDataSource.loadTrending())
}