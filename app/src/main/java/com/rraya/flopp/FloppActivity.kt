package com.rraya.flopp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rraya.flopp.viewmodels.VideoGameViewModelFactory
import com.rraya.flopp.viewmodels.VideoGamesViewModel
import com.rraya.flopp.views.VideoGameDetailFragment
import com.rraya.flopp.views.VideoGameListFragment
import kotlinx.android.synthetic.main.flopp_main.*
import kotlin.reflect.KClass

class FloppActivity : FragmentActivity() {

    lateinit var videoGamesViewModel: VideoGamesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.flopp_main)
        setActionBar(toolbar)

        videoGamesViewModel = ViewModelProvider(
            this,
            VideoGameViewModelFactory(this)
        ).get(VideoGamesViewModel::class.java)

        videoGamesViewModel.currentFragment.observe(this, Observer {
            changeFragment(it!!, true)
        })

        changeFragment(VideoGameListFragment::class, false)
    }

    private fun changeFragment(
        fragmentClass: KClass<out Fragment>,
        addToBackStack: Boolean
    ): Boolean {

        var fragment: Fragment? = supportFragmentManager.findFragmentByTag(fragmentClass.toString())

        if (fragment == null) {
            fragment = when (fragmentClass) {
                VideoGameDetailFragment::class -> {
                    VideoGameDetailFragment()
                }
                VideoGameListFragment::class -> {
                    VideoGameListFragment()
                }
                else -> {
                    null
                }
            }
        }
        if (fragment == null) {
            return false
        }

        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment, fragmentClass.toString())

        if (addToBackStack) {
            transaction.addToBackStack(fragmentClass.toString())
        }

        transaction.commit()

        return true
    }
}
