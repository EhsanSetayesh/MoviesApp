package com.ehsansetayesh.core.netwok.api

import com.ehsansetayesh.core.common.data.model.result.NetworkResult
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class NetworkResultCall<T : Any>(
    private val proxy: Call<T>,
    private val scope: CoroutineScope,
    private val gson: Gson
) : Call<NetworkResult<T>> {

    override fun enqueue(callback: Callback<NetworkResult<T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                scope.launch {
                    try {
                        val networkResult = handleApi(gson) { response }
                        callback.onResponse(this@NetworkResultCall, Response.success(networkResult))
                    } catch (e: Exception) {
                        val networkResult = NetworkResult.Exception<T>(e)
                        callback.onResponse(this@NetworkResultCall, Response.success(networkResult))
                    }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val networkResult = NetworkResult.Exception<T>(t)
                callback.onResponse(this@NetworkResultCall, Response.success(networkResult))
            }
        })
    }

    override fun execute(): Response<NetworkResult<T>> = throw NotImplementedError()
    override fun clone(): Call<NetworkResult<T>> =
        NetworkResultCall(proxy = proxy.clone(), scope = scope, gson = gson)

    override fun request(): Request = proxy.request()
    override fun timeout(): Timeout = proxy.timeout()
    override fun isExecuted(): Boolean = proxy.isExecuted
    override fun isCanceled(): Boolean = proxy.isCanceled
    override fun cancel() = proxy.cancel()
}