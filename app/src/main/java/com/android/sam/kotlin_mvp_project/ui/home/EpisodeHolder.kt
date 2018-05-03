package com.android.sam.kotlin_mvp_project.ui.home

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.android.sam.kotlin_mvp_project.R
import com.android.sam.kotlin_mvp_project.ui.models.EpisodeViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.episodes_item_list.view.*

/**
 * Created by oussamabentalha on 02/05/2018.
 */
class EpisodeHolder(val view: View, val listener: Listener) : RecyclerView.ViewHolder(view) {

    fun bindData(data: EpisodeViewModel) {
        Glide
                .with(view.context)
                .load(data.urlImg)
                .into(view.picture)

        view.series_title.text = data.title
        view.code.text = data.code
        view.description.text = data.description
        view.setOnClickListener { listener.onClickItems(adapterPosition) }
        manageFavorite(data.isFavourite)

        view.favorite.setOnClickListener {
            listener.onClickFavorite(adapterPosition)
        }
    }


    private fun manageFavorite(isFavourite: Boolean) {
        view.favorite.setImageDrawable(ContextCompat.getDrawable(view.context,
                if (isFavourite) R.drawable.ic_favorite else R.drawable.ic_favorite_border))
    }

    interface Listener {
        fun onClickItems(position: Int)

        fun onClickFavorite(position: Int)
    }
}