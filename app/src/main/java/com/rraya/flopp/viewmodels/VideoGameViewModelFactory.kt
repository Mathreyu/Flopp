package com.rraya.flopp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rraya.flopp.data.VideoGameRepository

class VideoGameViewModelFactory (
    private val context: Context
    ) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return VideoGamesViewModel(context) as T
        }
}