package com.android.sam.kotlin_mvp_project.data.preference

import com.android.sam.kotlin_mvp_project.data.preference.models.EpisodePreference
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by oussamabentalha on 25/04/2018.
 */
interface Preference {
    fun saveFavorite(episodePreference: EpisodePreference): Completable
    fun getAllFavorite(): Single<List<EpisodePreference>>
    fun removeFavorite(episodePreference: EpisodePreference): Completable
    fun isFavorite(episodePreference: EpisodePreference): Single<Boolean>
}