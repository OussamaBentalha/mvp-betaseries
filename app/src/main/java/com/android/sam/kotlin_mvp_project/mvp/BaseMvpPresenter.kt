package com.android.sam.kotlin_mvp_project.mvp

/**
 * Created by oussamabentalha on 02/05/2018.
 */
interface BaseMvpPresenter<in V : BaseMvpView> {

    fun attachView(view: V)
    fun detachView()
}