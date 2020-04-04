package com.rraya.flopp.views

import android.app.AlertDialog.Builder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.rraya.flopp.FloppActivity
import com.rraya.flopp.R
import com.rraya.flopp.viewmodels.VideoGamesViewModel
import kotlinx.android.synthetic.main.video_game_detail_fragment.*

class VideoGameDetailFragment : Fragment() {

    private lateinit var videoGamesViewModel: VideoGamesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        videoGamesViewModel = (activity as FloppActivity).videoGamesViewModel
        return inflater.inflate(R.layout.video_game_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val glideOptions = RequestOptions()
            .error(R.drawable.ic_sync_problem_black_24dp)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH);

        videoGamesViewModel.videoGameDetails.observe(viewLifecycleOwner, Observer { gameItem ->
            if (gameItem != null) {
                gameTitle.text = gameItem.name
                gameDescription.text = gameItem.description
                Glide.with(view)
                    .load(gameItem.backgroundImage)
                    .apply(glideOptions)
                    .into(gameBackground)
            } else {
                Builder(context, R.style.DialogTheme)
                    .setTitle(getString(R.string.no_connection_error_title))
                    .setMessage(getString(R.string.no_connection_error_message))
                    .setNegativeButton("dismiss") { dialog, _ ->
                        activity?.onBackPressed()
                        dialog.dismiss()
                    }.create().show()
            }
        })
    }
}