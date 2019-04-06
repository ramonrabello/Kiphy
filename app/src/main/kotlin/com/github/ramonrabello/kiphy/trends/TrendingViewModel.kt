package com.github.ramonrabello.kiphy.trends

import androidx.lifecycle.ViewModel
import com.github.ramonrabello.kiphy.BuildConfig
import com.github.ramonrabello.kiphy.common.arch.state.Result
import com.github.ramonrabello.kiphy.common.arch.state.SingleLiveEvent
import com.github.ramonrabello.kiphy.common.arch.state.UiState
import com.github.ramonrabello.kiphy.common.extensions.postEmptyState
import com.github.ramonrabello.kiphy.common.extensions.postErrorState
import com.github.ramonrabello.kiphy.common.extensions.postLoadedState
import com.github.ramonrabello.kiphy.common.extensions.postLoadingState
import com.github.ramonrabello.kiphy.common.data.ApiProvider
import com.github.ramonrabello.kiphy.trends.data.TrendingDataSource
import com.github.ramonrabello.kiphy.trends.data.TrendingRepository
import com.github.ramonrabello.kiphy.trends.model.TrendingResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TrendingViewModel : ViewModel() {

    // TODO: move to a BaseViewModel
    private val _trendsDataEvent by lazy { SingleLiveEvent<Result<TrendingResponse>>() }
    val trendsDataEvent: SingleLiveEvent<Result<TrendingResponse>>
        get() = _trendsDataEvent

    // TODO: move to a BaseViewModel
    private val _uiStateEvent by lazy { SingleLiveEvent<UiState>() }
    val uiStateEvent: SingleLiveEvent<UiState>
        get() = _uiStateEvent

    private val repository by lazy {
        val api = ApiProvider.providesTrendingApi()
        TrendingRepository(TrendingDataSource.Remote(api))
    }

    // TODO: move to a BaseViewModel
    private val compositeDisposable by lazy { CompositeDisposable() }

    fun loadTrending() {
        if (BuildConfig.GIPHY_API_KEY.isEmpty()) {
            _uiStateEvent.postErrorState()
            _trendsDataEvent.postValue(Result.Error.ApiKeyNotSetError)
        } else {
            val disposable = repository.loadTrending()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { _uiStateEvent.postLoadingState() }
                    .doOnSuccess { trending -> onTrendingLoaded(trending) }
                    .doOnError { error -> onError(error) }
                    .subscribe()
            compositeDisposable.add(disposable)
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

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}