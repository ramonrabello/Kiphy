package com.github.ramonrabello.kiphy.trends

import com.github.ramonrabello.kiphy.trends.model.TrendingResponse

/**
 * MVP contract for Giphy Trending API.
 */
interface TrendingContract {

    /**
     * View for Trending contract.
     */
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showTrending(trending: TrendingResponse)
        fun showApiKeyNotSetDialog()
        fun showTrendingError()
    }

    /**
     * Presenter for Trending contract.
     */
    interface Presenter {
        fun loadTrends()
    }
}