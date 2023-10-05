package com.ddd.filmo.network.factory

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type

class ApiResultCallAdapter<R>(
    private val responseType: Type,
) : CallAdapter<R, Call<ApiResult<R>>> {
    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): Call<ApiResult<R>> = ApiResultCall(call, responseType)

    private class ApiResultCall<R>(
        private val call: Call<R>,
        private val successType: Type,
    ) : Call<ApiResult<R>> {

        override fun enqueue(callback: Callback<ApiResult<R>>) = call.enqueue(
            object : Callback<R> {

                override fun onResponse(call: Call<R>, response: Response<R>) {
                    callback.onResponse(
                        this@ApiResultCall,
                        Response.success(response.toApiResult()),
                    )
                }

                private fun Response<R>.toApiResult(): ApiResult<R> {
                    // Http error response (4xx - 5xx)
                    if (!isSuccessful) {
                        val errorBody = errorBody()!!.string()
                        return ApiResult.Failure.HttpFailure(
                            code = code(),
                            message = message(),
                            errorBody = errorBody,
                        )
                    }

                    // Http success response with body
                    body()?.let { body -> return ApiResult.successOf(body) }

                    // if we defined Unit as success type it means we expected no response body
                    // e.g. in case of 204 No Content
                    return if (successType == Unit::class.java) {
                        @Suppress("UNCHECKED_CAST")
                        ApiResult.successOf(Unit as R)
                    } else {
                        ApiResult.Failure.UnknownFailure(
                            IllegalStateException(
                                "Response code is ${code()} but body is null.\n" +
                                    "If you expect response body to be null then define your API method as returning Unit:\n" +
                                    "@POST fun postSomething(): ApiResult<Unit>",
                            ),
                        )
                    }
                }

                override fun onFailure(call: Call<R?>, throwable: Throwable) {
                    val error = if (throwable is IOException) {
                        ApiResult.Failure.NetworkFailure(throwable)
                    } else {
                        ApiResult.Failure.UnknownFailure(throwable)
                    }
                    callback.onResponse(this@ApiResultCall, Response.success(error))
                }
            },
        )

        override fun clone(): Call<ApiResult<R>> = ApiResultCall(call.clone(), successType)

        override fun execute(): Response<ApiResult<R>> =
            throw UnsupportedOperationException("This adapter does not support sync execution")

        override fun isExecuted(): Boolean = call.isExecuted

        override fun cancel() = call.cancel()

        override fun isCanceled(): Boolean = call.isCanceled

        override fun request(): Request = call.request()

        override fun timeout(): Timeout = call.timeout()
    }
}
