package com.android.sam.kotlin_mvp_project.ui.home

import com.android.sam.kotlin_mvp_project.data.betaseries.BetaSeriesApi
import com.android.sam.kotlin_mvp_project.data.data.betaseries.models.Series
import com.android.sam.kotlin_mvp_project.data.preference.Preference
import com.android.sam.kotlin_mvp_project.data.preference.models.EpisodePreference
import com.android.sam.kotlin_mvp_project.mvp.BaseMvpPresenterImpl
import com.android.sam.kotlin_mvp_project.ui.Navigator
import com.android.sam.kotlin_mvp_project.ui.models.EpisodeViewModel
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.toSingle

/**
 * Created by oussamabentalha on 02/05/2018.
 */
class HomePresenter(val view: HomeContract.View, val navigator: Navigator,
                    private val betaSeriesApi: BetaSeriesApi,
                    private val preference: Preference) : BaseMvpPresenterImpl<HomeContract.View>(), HomeContract.Presenter {

    companion object {
        val PICTURE_URL = "https://api.betaseries.com/pictures/episodes?v=3.0&key=f6db37cffd53&id="
    }

    lateinit var episodeViewModels:MutableList<EpisodeViewModel>

    override fun fetchDetails(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun login() {
        betaSeriesApi.login().observeOn(AndroidSchedulers.mainThread())
                .subscribe({ displayUnseenEpisode() },
                {
                    view.showErrorMessage(it.localizedMessage)
                    throw it
                })
    }

    private fun fetchFavoritesEpisode(): Single<List<EpisodePreference>> = preference.getAllFavorite()

    private fun fetchUnseenEpisode(): Single<List<Series>> = betaSeriesApi.getUnseenEpisodes()

    private fun displayUnseenEpisode(){
        val singleApi = fetchUnseenEpisode()
        val singlePref = fetchFavoritesEpisode()

        singleApi.toSingle().observeOn(AndroidSchedulers.mainThread()).subscribe( { result -> combineSingleData(result.blockingGet()) }, { error -> view.showErrorMessage(error.localizedMessage)})
        //TODO
        /*Observables.zip(singleApi.toObservable(), singlePref.toObservable(), this::combineData)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::setData, { view.showErrorMessage(it.localizedMessage) })*/
    }

    fun combineSingleData(series: List<Series>){
        episodeViewModels = apiDataConverter(series).toMutableList()
        view.setData(apiDataConverter(series))
    }

    override fun favoriteClicked(position: Int) {
        val item = episodeViewModels[position]
        val completable: Completable = if (item.isFavourite) removeFavorite(item) else addFavorite(item)

        completable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    episodeViewModels[position] = item.copy(isFavourite = !item.isFavourite)
                    view.updateItemAtPosition(episodeViewModels[position], position)
                }
    }

    /**
     *  **
     * Business logic
     */
    private fun combineData(unseenEpisodesBySeries: List<Series>, favoriteEpisode: List<EpisodePreference>):
            List<EpisodeViewModel> {

        val list = apiDataConverter(unseenEpisodesBySeries)

        this.episodeViewModels = list.map {
            var episode = it
            favoriteEpisode.forEach { favorite ->
                if (it.id == favorite.id) {
                    episode = it.copy(isFavourite = true)
                }
            }

            episode
        }.toMutableList()

        return this.episodeViewModels
    }

    private fun removeFavorite(episodeViewModel: EpisodeViewModel): Completable {
        return preference.removeFavorite(episodePreferenceFromEpisodeViewModel(episodeViewModel))
    }

    private fun addFavorite(episodeViewModel: EpisodeViewModel): Completable {
        return preference
                .saveFavorite(episodePreferenceFromEpisodeViewModel(episodeViewModel))
    }

    /**
     * *
     *  PreferenceModel and ViewModel used here
     */
    private fun episodePreferenceFromEpisodeViewModel(episodeViewModel: EpisodeViewModel): EpisodePreference =
            EpisodePreference(episodeViewModel.id)

    /**
     *  *
     *  ApiModel and ViewModel used here
     */
    private fun apiDataConverter(series: List<Series>): List<EpisodeViewModel> {
        return series.flatMap {
            val mutableList = mutableListOf<EpisodeViewModel>()

            it.unseen.forEach { episode ->
                mutableList.add(EpisodeViewModel(episode.id, PICTURE_URL + episode.id,
                        it.title, episode.code, episode.description))
            }

            return mutableList
        }
    }

}