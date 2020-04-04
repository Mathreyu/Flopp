package com.rraya.flopp.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.rraya.flopp.R
import com.rraya.flopp.data.models.VideoGameLight
import kotlinx.android.synthetic.main.videogame_item.view.*

class VideoGamesAdapter(
    private val listener: (VideoGameLight) -> Unit
) : ListAdapter<VideoGameLight, VideoGamesAdapter.VideoGameHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoGameHolder {
        return VideoGameHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.videogame_item,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: VideoGameHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class VideoGameHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var glideOptions : RequestOptions = RequestOptions()
            .dontTransform()
            .placeholder(R.drawable.ic_sync_problem_black_24dp)
            .error(R.drawable.ic_sync_problem_black_24dp)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)

        fun bind(item: VideoGameLight, action: (VideoGameLight) -> Unit) = with(itemView) {
            gameName.text = item.name
            metacriticScore.text = "Metacritic Score: ${item.metacriticScore}"

            Glide.with(itemView)
                .load(item.backgroundImage)
                .apply(glideOptions)
                .into(gameImage)
//                .onLoadFailed(resources.getDrawable(R.drawable.ic_launcher_background, null))

            itemView.setOnClickListener {
                action(item)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<VideoGameLight>() {
        override fun areItemsTheSame(oldItem: VideoGameLight, newItem: VideoGameLight): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: VideoGameLight, newItem: VideoGameLight): Boolean {
            return oldItem.name == newItem.name
        }
    }

}