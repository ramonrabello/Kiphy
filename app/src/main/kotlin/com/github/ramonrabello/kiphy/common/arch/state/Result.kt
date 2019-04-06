package com.github.ramonrabello.kiphy.common.arch.state

/**
 * Sealed class that represents data state whether if
 * it is success or errors.
 */
sealed class Result<out T : Any> {
    class Success<out T : Any>(val value: T) : Result<T>()
    sealed class Error : Result<Nothing>() {
        class NetworkError(error: Throwable) : Error()
        object TimeoutError : Error()
        object ApiResponseError : Error()
        object ApiKeyNotSetError : Error()
        sealed class FeatureError : Error() {
            object TrendingError : FeatureError()
        }
    }
}