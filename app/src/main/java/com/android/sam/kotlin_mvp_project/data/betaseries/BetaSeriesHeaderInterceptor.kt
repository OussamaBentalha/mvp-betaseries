package com.android.sam.kotlin_mvp_project.data.betaseries

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by oussamabentalha on 25/04/2018.
 */
class BetaSeriesHeaderInterceptor : Interceptor {

    companion object {
        private val API_KEY = "f6db37cffd53"
        private val API_V = "3.0"
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()

        val newRequest = original.newBuilder()
                .header("X-BetaSeries-Version", API_V)
                .header("X-BetaSeries-Key", API_KEY)
                .build()

        return chain.proceed(newRequest)
    }

}