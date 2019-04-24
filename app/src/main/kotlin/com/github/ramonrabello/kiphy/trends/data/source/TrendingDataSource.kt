package com.github.ramonrabello.kiphy.trends.data.source

import com.github.ramonrabello.kiphy.trends.model.TrendingResponse
import io.reactivex.Single

interface TrendingDataSource {

    fun loadTrending(): Single<TrendingResponse>
}