package com.ehsansetayesh.core.netwok.api

import com.ehsansetayesh.core.common.data.model.result.NetworkResult
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class NetworkResultCallAdapter(
    private val resultType: Type,
    private val scope: CoroutineScope,
    private val gson: Gson
) : CallAdapter<Type, Call<NetworkResult<Type>>> {

    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type>): Call<NetworkResult<Type>> {
        return NetworkResultCall(proxy = call, scope = scope, gson = gson)
    }
}