package com.android.sam.kotlin_mvp_project.mvp

import android.content.Context

/**
 * Created by oussamabentalha on 02/05/2018.
 */
interface BaseMvpView {

    fun getContext(): Context

    fun showError(error: String?)

    fun showMessage(message: String)

}