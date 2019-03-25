package com.github.ramonrabello.kiphy.trends

import com.github.ramonrabello.kiphy.BuildConfig
import com.github.ramonrabello.kiphy.data.GiphyApi
import com.github.ramonrabello.kiphy.data.model.Trending
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Presenter implementation for TrendingContract.Presenter.
 */
class TrendingPresenter(private val view:TrendingContract.View): TrendingContract.Presenter {

    override fun loadTrending() {
        view.showProgress()

        if (BuildConfig.GIPHY_API_KEY.isEmpty() ||
                BuildConfig.GIPHY_API_KEY == "PASTE_YOUR_API_KEY_HERE"){
            view.hideProgress()
            view.showApikeyError()
        }

        GiphyApi.trending().load().enqueue(object: Callback<Trending> {

            override fun onResponse(call: Call<Trending>, response: Response<Trending>) {
                if (response.isSuccessful){
                    view.hideProgress()
                    response.body()?.let { view.showTrending(it) }
                } else {
                    view.showTrendingError()
                }
            }

            override fun onFailure(call: Call<Trending>, t: Throwable) {
                view.showTrendingError()
            }
        })
    }

    override fun onTrendingClick(view: TrendingContract.View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}