package com.github.ramonrabello.kiphy.trends

import androidx.lifecycle.LiveData
import com.github.ramonrabello.kiphy.BuildConfig
import com.github.ramonrabello.kiphy.common.arch.state.Result
import com.github.ramonrabello.kiphy.common.arch.state.SingleLiveEvent
import com.github.ramonrabello.kiphy.common.arch.state.UiState
import com.github.ramonrabello.kiphy.common.arch.viewmodel.BaseViewModel
import com.github.ramonrabello.kiphy.common.data.ApiProvider
import com.github.ramonrabello.kiphy.common.extensions.postEmptyState
import com.github.ramonrabello.kiphy.common.extensions.postErrorState
import com.github.ramonrabello.kiphy.common.extensions.postLoadedState
import com.github.ramonrabello.kiphy.common.extensions.postLoadingState
import com.github.ramonrabello.kiphy.trends.data.TrendingDataSource
import com.github.ramonrabello.kiphy.trends.data.TrendingRepository
import com.github.ramonrabello.kiphy.trends.model.TrendingResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TrendingViewModel : BaseViewModel() {

    private val _trendsDataEvent by lazy { SingleLiveEvent<Result<TrendingResponse>>() }
    val trendsDataEvent: LiveData<Result<TrendingResponse>>
        get() = _trendsDataEvent

    private val _uiStateEvent by lazy { SingleLiveEvent<UiState>() }
    val uiStateEvent: LiveData<UiState>
        get() = _uiStateEvent

    private val repository by lazy {
        val api = ApiProvider.providesTrendingApi()
        TrendingRepository(
                TrendingDataSource.Remote(api),
                TrendingDataSource.Local()
        )
    }

    fun loadTrending() {
        if (BuildConfig.GIPHY_API_KEY.isEmpty()) {
            _uiStateEvent.postErrorState()
            _trendsDataEvent.postValue(Result.Error.ApiKeyNotSetError)
        } else {
            repository.loadTrending()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { _uiStateEvent.postLoadingState() }
                    .subscribe(
                            { trending -> onTrendingLoaded(trending) },
                            { error -> onError(error) })
                    .safeDispose()
        }
    }

    private fun onTrendingLoaded(trending: TrendingResponse) {
        if (trending.data.isEmpty()) {
            _uiStateEvent.postEmptyState()
        } else {
            _trendsDataEvent.postValue(Result.Success(trending))
            _uiStateEvent.postLoadedState()
        }
    }

    private fun onError(error: Throwable) {
        _uiStateEvent.postErrorState()
        _trendsDataEvent.postValue(Result.Error.NetworkError(error))
    }
}