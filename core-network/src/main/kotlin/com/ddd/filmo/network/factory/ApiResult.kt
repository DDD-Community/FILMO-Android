package com.ddd.filmo.network.factory

sealed interface ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>

    sealed interface Failure : ApiResult<Nothing> {
        data class HttpFailure(val code: Int, val message: String, val errorBody: String) : Failure
        data class NetworkFailure(val exception: Throwable) : Failure
        data class UnknownFailure(val exception: Throwable) : Failure
    }

    fun isSuccess(): Boolean = this is Success

    fun isFailure(): Boolean = this is Failure

    companion object {
        fun <R> successOf(result: R): ApiResult<R> = Success(result)
    }
}

fun <T> ApiResult<T>.handlingNetwork(): T {
    return when (this) {
        is ApiResult.Success -> this.data
        is ApiResult.Failure.HttpFailure -> throw Exception()
        is ApiResult.Failure.NetworkFailure -> throw Exception()
        is ApiResult.Failure.UnknownFailure -> throw Exception()
    }
}

inline fun <T> handlingNetwork(action: (() -> ApiResult<T>)): T {
    return when (action.invoke()) {
        is ApiResult.Success -> (action.invoke() as ApiResult.Success).data
        is ApiResult.Failure.HttpFailure -> throw Exception()
        is ApiResult.Failure.NetworkFailure -> throw Exception()
        is ApiResult.Failure.UnknownFailure -> throw Exception()
    }
}

inline fun <T> ApiResult<T>.onSuccess(
    action: (value: T) -> Unit,
): ApiResult<T> {
    if (isSuccess()) action((this as ApiResult.Success).data)
    return this
}

/**
 *
 *
 * @param T
 * @param action
 * @return 자기자신을 반환함으로써 체이닝 가능
 */
inline fun <T> ApiResult<T>.onFailure(
    action: (error: ApiResult.Failure) -> Unit,
): ApiResult<T> {
    if (isFailure()) action(this as ApiResult.Failure)
    return this
}
