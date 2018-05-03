package com.android.sam.kotlin_mvp_project.ui.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.sam.kotlin_mvp_project.R
import com.android.sam.kotlin_mvp_project.ui.models.EpisodeViewModel

/**
 * Created by oussamabentalha on 02/05/2018.
 */
class EpisodesAdapter(private val episodes: MutableList<EpisodeViewModel>,
                      private val listener: EpisodeHolder.Listener) :
        RecyclerView.Adapter<EpisodeHolder>() {

    fun updateItemForPosition(position: Int, item: EpisodeViewModel) {
        episodes[position] = item
        notifyItemChanged(position)
    }

    override fun onBindViewHolder(holder: EpisodeHolder, position: Int) {
        holder.bindData(episodes[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeHolder =
            EpisodeHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.episodes_item_list, parent, false), listener)

    override fun getItemCount(): Int = episodes.size
}