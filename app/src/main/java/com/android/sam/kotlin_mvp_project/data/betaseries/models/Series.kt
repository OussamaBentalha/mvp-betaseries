package com.android.sam.kotlin_mvp_project.data.data.betaseries.models

import com.android.sam.kotlin_mvp_project.data.betaseries.models.Episode

/**
 * Created by oussamabentalha on 25/04/2018.
 */
data class Series(val id:String, val title: String, val unseen: List<Episode>)