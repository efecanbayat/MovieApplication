package com.efecanbayat.movieapplication.di.interceptor

import com.efecanbayat.movieapplication.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val httpUrl = request.url.newBuilder()
            .addQueryParameter("api_key", Constants.API_KEY)
            .build()

        request = request.newBuilder().url(httpUrl).build()
        return chain.proceed(request)
    }
}