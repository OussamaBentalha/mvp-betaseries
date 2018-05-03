package com.android.sam.kotlin_mvp_project.ui

import android.app.Fragment
import android.app.FragmentManager
import android.content.Context
import com.android.sam.kotlin_mvp_project.ui.home.HomeFragment

/**
 * Created by oussamabentalha on 02/05/2018.
 */
class AppFragmentNavigator(val context: Context, val fragmentManager: FragmentManager, val containerId : Int) : Navigator {

    override fun displayHome() {
        var fragment = HomeFragment()
        displayAndSetRootFragment(fragment)
    }

    override fun displayDetails() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayAndSetRootFragment(fragment : Fragment) {
        fragmentManager
                .beginTransaction()
                .add(containerId, fragment)
                .commit()
    }

    fun displayOrRestoreScreenOnActivityCreate(isAppInitialized: Boolean) {
        //TODO
        displayHome()
    }

    override fun onBackPressed() : Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return true;
    }

}