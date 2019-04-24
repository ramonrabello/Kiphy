package com.github.ramonrabello.kiphy.common.extensions

import com.github.ramonrabello.kiphy.common.arch.state.SingleLiveEvent
import com.github.ramonrabello.kiphy.common.arch.state.UiState

fun SingleLiveEvent<UiState>.postEmptyState() = run { postValue(UiState.Empty) }
fun SingleLiveEvent<UiState>.postLoadingState() = run { postValue(UiState.Loading) }
fun SingleLiveEvent<UiState>.postLoadedState() = run { postValue(UiState.Loaded) }
fun SingleLiveEvent<UiState>.postErrorState() = run { postValue(UiState.Error) }