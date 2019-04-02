package com.github.ramonrabello.kiphy.common.arch.state

/**
 * Sealed class that represents UI states.
 */
sealed class UiState {
    object Empty : UiState()
    object Loading : UiState()
    object Error : UiState()
    object Loaded : UiState()
    object LoadingMore : UiState()
}