package com.example.youtubeapi.ui.playlist

import androidx.lifecycle.LiveData
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.core.uiBase.BaseViewModel
import com.example.youtubeapi.data.models.Playlist
import com.example.youtubeapi.repositories.Repository

class MyPlaylistViewModel(private val repository: Repository ):BaseViewModel() {


    fun getPlaylists(): LiveData<Resource<Playlist>> {
        return  repository.getPlaylists()
    }

}