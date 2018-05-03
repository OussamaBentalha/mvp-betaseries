package com.android.sam.kotlin_mvp_project.data

import com.android.sam.kotlin_mvp_project.data.betaseries.betaSeriesKodeinModule
import com.android.sam.kotlin_mvp_project.data.preference.preferenceKodeinModule
import com.github.salomonbrys.kodein.Kodein

/**
 * Created by oussamabentalha on 02/05/2018.
 */
val dataKodeinModule = Kodein.Module {
    import(betaSeriesKodeinModule)
    import(preferenceKodeinModule)
}