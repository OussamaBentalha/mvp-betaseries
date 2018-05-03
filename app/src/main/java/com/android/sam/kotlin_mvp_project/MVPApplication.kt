package com.android.sam.kotlin_mvp_project

import android.app.Application
import android.content.Context
import com.android.sam.kotlin_mvp_project.data.dataKodeinModule
import com.android.sam.kotlin_mvp_project.ui.uiKodeinModule
import com.github.salomonbrys.kodein.*

/**
 * Created by oussamabentalha on 02/05/2018.
 */
class MVPApplication : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        bind<Context>("applicationContext") with instance(applicationContext)
        import(dataKodeinModule)
        import(uiKodeinModule)
    }
}