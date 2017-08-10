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

    override fun loadTrending() {
        view.showProgress()

        GiphyApi.trending().load().enqueue(object: Callback<Trending> {

            override fun onResponse(call: Call<Trending>, response: Response<Trending>) {
                if (response.isSuccessful){
                    view.hideProgress()
                    view.showTrending(response.body())
                } else {
                    view.onLoadingTrendsError()
                }
            }

            override fun onFailure(call: Call<Trending>, t: Throwable) {
                view.onLoadingTrendsError()
            }
        })
    }

    override fun onTrendingClick(view: TrendingContract.View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}