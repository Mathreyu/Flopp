package com.rraya.flopp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.rraya.flopp.FloppActivity
import com.rraya.flopp.R
import com.rraya.flopp.data.models.VideoGameLight
import com.rraya.flopp.viewmodels.VideoGamesViewModel
import kotlinx.android.synthetic.main.videogame_list_fragment.*

class VideoGameListFragment : Fragment() {

    private lateinit var videoGamesViewModel: VideoGamesViewModel

    private var adapter: VideoGamesAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        videoGamesViewModel = (activity as FloppActivity).videoGamesViewModel
        return inflater.inflate(R.layout.videogame_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (adapter == null) {
            adapter = VideoGamesAdapter(this::handleClick)
        }
        rvVideoGames.layoutManager = GridLayoutManager(this.activity, 1)
        rvVideoGames.adapter = adapter
        setObservers()
    }

    private fun handleClick(item: VideoGameLight) {
        videoGamesViewModel.selectedGame.value = item.id
        videoGamesViewModel.currentFragment.value = VideoGameDetailFragment::class
    }

    private fun setObservers() {
        videoGamesViewModel.videoGameData.observe(viewLifecycleOwner, Observer {
            adapter!!.submitList(it)
            adapter!!.notifyDataSetChanged()
        })
    }
}