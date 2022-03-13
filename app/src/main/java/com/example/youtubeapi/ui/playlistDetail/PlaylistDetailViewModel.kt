package com.example.youtubeapi.ui.playlistDetail

import androidx.lifecycle.LiveData
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.core.uiBase.BaseViewModel
import com.example.youtubeapi.data.models.Playlist
import com.example.youtubeapi.repositories.Repository

class PlaylistDetailViewModel(private val repository: Repository):BaseViewModel() {

    fun getPlaylistItems(playlistId: String, pageToken: String?): LiveData<Resource<Playlist>> {
        return repository.getPlaylistsItem(playlistId,pageToken)
    }

}