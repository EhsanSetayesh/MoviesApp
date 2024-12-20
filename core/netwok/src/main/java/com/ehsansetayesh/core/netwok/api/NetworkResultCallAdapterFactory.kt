package com.ehsansetayesh.core.netwok.api

import com.ehsansetayesh.core.common.data.model.result.NetworkResult
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResultCallAdapterFactory(
    private val scope: CoroutineScope,
    private val gson: Gson
) : CallAdapter.Factory() {

    override fun get(
        returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) {
            return null
        }

        val callType = getParameterUpperBound(0, returnType as ParameterizedType)
        if (getRawType(callType) != NetworkResult::class.java) {
            return null
        }

        val resultType = getParameterUpperBound(0, callType as ParameterizedType)
        return NetworkResultCallAdapter(resultType = resultType, scope = scope, gson = gson)
    }
}