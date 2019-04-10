package com.github.ramonrabello.kiphy.common.arch.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val compositeDisposable by lazy { CompositeDisposable() }

    fun Disposable.safeDispose() = compositeDisposable.add(this)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}