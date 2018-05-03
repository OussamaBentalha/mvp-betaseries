package com.android.sam.kotlin_mvp_project.ui.home

import com.android.sam.kotlin_mvp_project.mvp.BaseMvpPresenter
import com.android.sam.kotlin_mvp_project.mvp.BaseMvpView
import com.android.sam.kotlin_mvp_project.ui.models.EpisodeViewModel

/**
 * Created by oussamabentalha on 02/05/2018.
 */
interface HomeContract {

    interface View : BaseMvpView {
        fun setData(episodes: List<EpisodeViewModel>)
        fun updateItemAtPosition(item: EpisodeViewModel, position: Int)
        fun showErrorMessage(message: String)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun fetchDetails(id: Int)
        fun login()
        fun favoriteClicked(position: Int)
    }

}