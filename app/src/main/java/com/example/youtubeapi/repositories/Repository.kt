package com.example.youtubeapi.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.utils.Constant
import com.example.youtubeapi.data.models.Playlist
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.data.remote.ApiService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers

class Repository(private val apiService:ApiService) {

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

    fun getVideos(videoId:String):LiveData<Resource<Playlist>> = liveData(Dispatchers.IO+coroutineExceptionHandler){
        emit(Resource.loading(null))
        val response = apiService.getVideos(Constant.part,videoId,BuildConfig.API_KEY)
        emit(if (response.isSuccessful) Resource.success(response.body())else Resource.error(response.message()))
    }

    fun getPlaylistsItem(playlistId: String, pageToken: String?):LiveData<Resource<Playlist>> = liveData(Dispatchers.IO+coroutineExceptionHandler) {
      emit(Resource.loading(null))
        val response = apiService.getPlaylistItems(Constant.part,playlistId,BuildConfig.API_KEY,Constant.maxResults,pageToken)
        emit(if (response.isSuccessful) Resource.success(response.body()) else Resource.error(response.message()))

    }

    fun getPlaylists():LiveData<Resource<Playlist>> = liveData(Dispatchers.IO+coroutineExceptionHandler){
        emit(Resource.loading(null ))
        val response = apiService.getPlaylists(Constant.part, Constant.channelId, BuildConfig.API_KEY, Constant.maxResults)
        emit(if (response.isSuccessful) Resource.success(response.body()) else Resource.error(response.message()))

    }

}