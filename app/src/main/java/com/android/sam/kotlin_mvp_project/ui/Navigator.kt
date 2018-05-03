package com.android.sam.kotlin_mvp_project.ui

import android.app.Fragment

/**
 * Created by oussamabentalha on 02/05/2018.
 */
interface Navigator {
    fun displayAndSetRootFragment(fragment: Fragment)
    fun onBackPressed() : Boolean
    fun displayHome()
    fun displayDetails()
}