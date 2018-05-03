package com.android.sam.kotlin_mvp_project.data.betaseries


import com.android.sam.kotlin_mvp_project.data.data.betaseries.models.Series
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by oussamabentalha on 25/04/2018.
 */
interface BetaSeriesApi {
    fun login(): Completable
    fun getUnseenEpisodes(): Single<List<Series>>
}