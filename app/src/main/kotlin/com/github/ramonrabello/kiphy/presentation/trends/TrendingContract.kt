package com.github.ramonrabello.kiphy.presentation.trends

import com.github.ramonrabello.kiphy.data.Trending

/**
 * MVP contract for Giphy Trending API.
 */
interface TrendingContract {

    /**
     * View for Trending contract.
     */
    interface View {
        fun showLoading()
        fun hideLoading()
        fun showTrends(trending: Trending?)
        fun onLoadingTrendsError()
    }

    /**
     * Presenter for Trending contract.
     */
    interface Presenter {
        fun loadTrends()
        fun searchTrends(query:String)
    }
}