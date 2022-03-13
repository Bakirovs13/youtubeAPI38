package com.example.youtubeapi.ui.video

import androidx.lifecycle.LiveData
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.core.uiBase.BaseViewModel
import com.example.youtubeapi.data.models.Playlist
import com.example.youtubeapi.repositories.Repository

class VideoViewModel (private val repository: Repository): BaseViewModel() {

    fun getVideos(videoId:String):LiveData<Resource<Playlist>>{
     return   repository.getVideos(videoId)
    }
}