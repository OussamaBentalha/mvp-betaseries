package com.android.sam.kotlin_mvp_project.data.betaseries

import com.android.sam.kotlin_mvp_project.data.betaseries.services.EpisodesService
import com.android.sam.kotlin_mvp_project.data.betaseries.services.MembersService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by oussamabentalha on 25/04/2018.
 */

fun buildBetaSeriesHttpClient(): OkHttpClient {
    val logInterceptor = HttpLoggingInterceptor()
    logInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .addInterceptor(BetaSeriesHeaderInterceptor())
            .build()
}

fun buildBetaSeriesRetrofit(httpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://api.betaseries.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}

fun buildMembersEndpoint(retrofit: Retrofit): MembersService {
    return retrofit.create(MembersService::class.java)
}

fun buildEpisodesEndpoint(retrofit: Retrofit): EpisodesService {
    return retrofit.create(EpisodesService::class.java)
}