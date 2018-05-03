package com.android.sam.kotlin_mvp_project.ui

import com.android.sam.kotlin_mvp_project.ui.home.HomeContract
import com.android.sam.kotlin_mvp_project.ui.home.HomePresenter
import com.github.salomonbrys.kodein.*

/**
 * Created by oussamabentalha on 02/05/2018.
 */
val uiKodeinModule = Kodein.Module {
    bind<HomePresenter>() with singleton { HomePresenter(instance(), instance(), instance(), instance()) }
}