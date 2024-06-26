package com.arcadone.pokestorie.rest.model

sealed class ApiResult<out T : Any> {
    companion object {
        fun <T : Any> success(data: T) = Success(data)
        fun error(throwable: Throwable) = Error(throwable)
    }

    data class Success<out T : Any> internal constructor(val data: T) : ApiResult<T>()
    data class Error internal constructor(val throwable: Throwable) : ApiResult<Nothing>()
}

inline fun <T : Any> ApiResult<T>.onSuccess(action: (T) -> Unit): ApiResult<T> {
    if (this is ApiResult.Success) action(data)
    return this
}

inline fun <T : Any> ApiResult<T>.onFailure(action: (Throwable) -> Unit): ApiResult<T> {
    if (this is ApiResult.Error) action(throwable)
    return this
}
