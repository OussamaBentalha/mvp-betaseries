package com.android.sam.kotlin_mvp_project.data.betaseries

import com.android.sam.kotlin_mvp_project.data.betaseries.services.EpisodesService
import com.android.sam.kotlin_mvp_project.data.betaseries.services.MembersService
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Created by oussamabentalha on 25/04/2018.
 */
val betaSeriesKodeinModule = Kodein.Module {
    bind<OkHttpClient>() with singleton { buildBetaSeriesHttpClient() }
    bind<Retrofit>() with singleton { buildBetaSeriesRetrofit(instance()) }
    bind<MembersService>() with singleton { buildMembersEndpoint(instance()) }
    bind<EpisodesService>() with singleton { buildEpisodesEndpoint(instance()) }

    bind<BetaSeriesApi>() with singleton { BetaSeriesApiImpl(instance(), instance()) }
}