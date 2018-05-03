package com.android.sam.kotlin_mvp_project.data.preference

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton

/**
 * Created by oussamabentalha on 25/04/2018.
 */
val preferenceKodeinModule = Kodein.Module {
    bind<Preference>() with singleton {
        PreferenceImpl(instance("applicationContext"))
    }
}