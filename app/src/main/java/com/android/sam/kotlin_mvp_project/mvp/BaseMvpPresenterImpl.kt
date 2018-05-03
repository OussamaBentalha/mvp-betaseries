package com.android.sam.kotlin_mvp_project.mvp

/**
 * Created by oussamabentalha on 02/05/2018.
 */
open class BaseMvpPresenterImpl<V : BaseMvpView> : BaseMvpPresenter<V> {
    protected var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }
}