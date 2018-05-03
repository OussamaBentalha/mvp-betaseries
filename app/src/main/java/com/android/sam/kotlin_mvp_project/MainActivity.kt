package com.android.sam.kotlin_mvp_project

import android.app.Activity
import android.os.Bundle
import com.android.sam.kotlin_mvp_project.ui.AppFragmentNavigator
import com.android.sam.kotlin_mvp_project.ui.Navigator
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.ActivityInjector
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance

class MainActivity : Activity(), ActivityInjector {

    override val injector: KodeinInjector = KodeinInjector()

    lateinit var navigator: AppFragmentNavigator

    companion object {
        private var isInitialized = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator = AppFragmentNavigator(this, this.fragmentManager, R.id.fragment_container)
        initializeInjector()
    }

    override fun provideOverridingModule() = Kodein.Module {
        bind<Navigator>() with instance(navigator)
    }

    override fun onResume() {
        super.onResume()
        navigator.displayOrRestoreScreenOnActivityCreate(isInitialized)
    }

    override fun onBackPressed() {
        val handled = navigator.onBackPressed()

        if (!handled) {
            super.onBackPressed()
        }
    }
}
