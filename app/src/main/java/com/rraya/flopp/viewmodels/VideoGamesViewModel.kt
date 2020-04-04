package com.rraya.flopp.viewmodels

import android.content.Context
import android.os.AsyncTask
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.rraya.flopp.data.VideoGameRepository
import com.rraya.flopp.data.models.VideoGameDetails
import com.rraya.flopp.data.models.VideoGameLight
import com.rraya.flopp.views.VideoGameDetailFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class VideoGamesViewModel(private val context: Context) : ViewModel() {
    val currentFragment = MutableLiveData<KClass<out Fragment>>()
    val videoGameDetails = MediatorLiveData<VideoGameDetails>()
    var selectedGame = MutableLiveData<String>()
    private val repository: VideoGameRepository = VideoGameRepository(context)

    init {
            videoGameDetails.addSource(selectedGame, Observer {
                CoroutineScope(Dispatchers.IO).launch {
                    videoGameDetails.postValue(repository.getVideoGameDetails(it))
                }
            })
    }

    val videoGameData : LiveData<List<VideoGameLight>> = liveData(Dispatchers.IO) {
        val retrievedData = repository.getVideoGames()
        emit(repository.getVideoGames())
    }
}