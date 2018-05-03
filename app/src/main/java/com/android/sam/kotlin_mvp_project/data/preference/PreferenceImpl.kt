package com.android.sam.kotlin_mvp_project.data.preference

import android.content.Context
import com.android.sam.kotlin_mvp_project.data.preference.models.EpisodePreference
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Created by oussamabentalha on 25/04/2018.
 */
class PreferenceImpl(context: Context) : Preference {

    companion object {
        private val PREF_NAME = "favorite_pref"
        private val FAVORITES_KEY = "favorites_key"
    }

    private val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()

    override fun saveFavorite(episodePreference: EpisodePreference): Completable {
        return Completable.fromAction({
            val list = getFavorites()
            list.add(episodePreference)

            editor.putString(FAVORITES_KEY, list.gsonSerialize())
            editor.commit()
        }).subscribeOn(Schedulers.io())
    }

    override fun getAllFavorite(): Single<List<EpisodePreference>> {
        return Single.fromCallable {
            val favoritesStr = sharedPref.getString(FAVORITES_KEY, "")
            favoritesStr.toListEpisodePreference()
        }.subscribeOn(Schedulers.io())
    }

    override fun removeFavorite(episodePreference: EpisodePreference): Completable {
        return Completable.fromAction({
            val list = getFavorites()
            if (list.contains(episodePreference)) {
                list.remove(episodePreference)
            }
            editor.putString(FAVORITES_KEY, list.gsonSerialize())
            editor.commit()
        }).subscribeOn(Schedulers.io())
    }

    override fun isFavorite(episodePreference: EpisodePreference): Single<Boolean> {
        return Single.fromCallable {
            getFavorites().contains(episodePreference)
        }.subscribeOn(Schedulers.io())
    }

    private fun getFavorites(): MutableList<EpisodePreference> {
        val list = mutableListOf<EpisodePreference>()

        val favoritesStr = sharedPref.getString(FAVORITES_KEY, "")
        if (favoritesStr.isNotEmpty()) {
            list.addAll(favoritesStr.toListEpisodePreference())
        }

        return list
    }

    private fun String.toListEpisodePreference(): List<EpisodePreference> {
        return Gson().fromJson(this, Array<EpisodePreference>::class.java).toList()
    }

    private fun List<EpisodePreference>.gsonSerialize(): String {
        return Gson().toJson(this)
    }

}