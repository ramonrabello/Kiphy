package com.github.ramonrabello.kiphy.presentation.trends

import com.github.ramonrabello.kiphy.data.GiphyApi
import com.github.ramonrabello.kiphy.data.Trending
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Presenter implementation for TrendingContract.Presenter.
 */
class TrendingPresenter(private val view:TrendingContract.View): TrendingContract.Presenter {

    override fun loadTrends() {
        view.showLoading()

        GiphyApi().trending().loadTrends().enqueue(object: Callback<Trending> {

            override fun onResponse(call: Call<Trending>, response: Response<Trending>) {
                if (response.isSuccessful){
                    view.hideLoading()
                    view.showTrends(response.body())
                } else {
                    view.onLoadingTrendsError()
                }
            }

            override fun onFailure(call: Call<Trending>, t: Throwable) {
                view.onLoadingTrendsError()
            }
        })
    }

    override fun searchTrends(query: String) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}