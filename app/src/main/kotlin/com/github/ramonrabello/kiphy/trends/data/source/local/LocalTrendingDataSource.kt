package com.github.ramonrabello.kiphy.trends.data.source.local

import com.github.ramonrabello.kiphy.trends.data.source.TrendingDataSource
import com.github.ramonrabello.kiphy.trends.model.TrendingResponse
import io.reactivex.Single

class LocalTrendingDataSource : TrendingDataSource {

    override fun loadTrending() = Single.fromCallable { TrendingResponse((emptyList())) }

}