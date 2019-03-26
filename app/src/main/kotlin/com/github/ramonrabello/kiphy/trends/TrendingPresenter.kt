package com.github.ramonrabello.kiphy.trends

import com.github.ramonrabello.kiphy.BuildConfig
import com.github.ramonrabello.kiphy.data.GiphyApi
import com.github.ramonrabello.kiphy.trends.model.TrendingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Presenter implementation for TrendingContract.Presenter.
 */
class TrendingPresenter(private val view:TrendingContract.View): TrendingContract.Presenter {

    override fun loadTrends() {
        view.showProgress()

        if (BuildConfig.GIPHY_API_KEY.isEmpty()){
            view.hideProgress()
            view.showApiKeyNotSetDialog()
        }

        GiphyApi.trending().load().enqueue(object: Callback<TrendingResponse> {

            override fun onResponse(call: Call<TrendingResponse>, response: Response<TrendingResponse>) {
                if (response.isSuccessful){
                    view.hideProgress()
                    response.body()?.let { body -> view.showTrending(body) }
                } else {
                    view.showTrendingError()
                }
            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                view.showTrendingError()
            }
        })
    }
}