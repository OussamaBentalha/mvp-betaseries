package com.android.sam.kotlin_mvp_project.data.betaseries

import com.android.sam.kotlin_mvp_project.data.betaseries.services.EpisodesService
import com.android.sam.kotlin_mvp_project.data.betaseries.services.MembersService
import com.android.sam.kotlin_mvp_project.data.data.betaseries.models.Series
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Created by oussamabentalha on 25/04/2018.
 */
class BetaSeriesApiImpl(private val episodesService: EpisodesService,
                        private val membersService: MembersService) : BetaSeriesApi {

    private var currentToken: String = ""

    companion object {
        private val LOGIN = "SamAlLuhaidan"
        private val PWD = "e1d43eb8a178c14293e315bb915c88d8"
    }

    override fun login(): Completable {
        return membersService.login(LOGIN, PWD)
                .subscribeOn(Schedulers.io())
                .map { currentToken = it.token }
                .toCompletable()
    }

    override fun getUnseenEpisodes(): Single<List<Series>> {
        return episodesService.getEpisodesUnseen(currentToken)
                .subscribeOn(Schedulers.io())
                .flatMap { Single.just(it.shows) }
    }
}