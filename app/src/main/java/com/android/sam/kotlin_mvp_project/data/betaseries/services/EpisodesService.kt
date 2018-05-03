package com.android.sam.kotlin_mvp_project.data.betaseries.services

import com.android.sam.kotlin_mvp_project.data.data.betaseries.models.UnseenEpisode
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Created by oussamabentalha on 25/04/2018.
 */
interface EpisodesService {

    @GET("episodes/list")
    fun getEpisodesUnseen(@Header("Authorization") token: String): Single<UnseenEpisode>
}