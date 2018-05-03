package com.android.sam.kotlin_mvp_project.ui.models

/**
 * Created by oussamabentalha on 02/05/2018.
 */
data class EpisodeViewModel(val id: String, val urlImg: String = "", val title: String = "", val code: String = "", val description: String = "", val isFavourite: Boolean = false)