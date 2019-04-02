package com.github.ramonrabello.kiphy.trends

import androidx.lifecycle.ViewModel
import com.github.ramonrabello.kiphy.BuildConfig
import com.github.ramonrabello.kiphy.common.arch.state.Result
import com.github.ramonrabello.kiphy.common.arch.state.SingleLiveEvent
import com.github.ramonrabello.kiphy.common.arch.state.UiState
import com.github.ramonrabello.kiphy.data.GiphyApi
import com.github.ramonrabello.kiphy.trends.model.TrendingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrendingViewModel : ViewModel() {

    private val _trendsDataEvent = SingleLiveEvent<Result<TrendingResponse>>()
    val trendsDataEvent: SingleLiveEvent<Result<TrendingResponse>>
        get() = _trendsDataEvent

    private val _uiStateEvent = SingleLiveEvent<UiState>()
    val uiStateEvent: SingleLiveEvent<UiState>
        get() = _uiStateEvent

    fun loadTrends() {
        _uiStateEvent.postValue(UiState.Loading)
        if (BuildConfig.GIPHY_API_KEY.isEmpty()) {
            _trendsDataEvent.postValue(Result.Error.ApiKeyNotSetError)
        }

        // TODO: extract to a repository
        GiphyApi.trending().load().enqueue(object : Callback<TrendingResponse> {
            override fun onResponse(call: Call<TrendingResponse>, response: Response<TrendingResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { trending ->
                        _uiStateEvent.postValue(UiState.Loaded)
                        _trendsDataEvent.postValue(Result.Success(trending))
                    }
                } else {
                    handleUiError()
                }
            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                handleUiError()
            }
        })
    }

    private fun handleUiError() {
        _uiStateEvent.postValue(UiState.Error)
        _trendsDataEvent.postValue(Result.Error.FeatureError.TrendingError)
    }
}