package com.example.youtubeapi.ui.Playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubeapi.BuildConfig.API_KEY
import com.example.youtubeapi.`object`.Constant
import com.example.youtubeapi.base.BaseViewModel
import com.example.youtubeapi.data.models.Playlist
import com.example.youtubeapi.data.remote.ApiService
import com.example.youtubeapi.data.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistViewModel:BaseViewModel() {

    private val apiService:ApiService by lazy{
        RetrofitClient.create()
    }

    fun playlists():LiveData<Playlist> {
        return getPlaylists()
    }

    private fun getPlaylists(): LiveData<Playlist> {

        val data= MutableLiveData<Playlist>()

        apiService.getPlaylists(Constant.part,Constant.channelId,API_KEY,Constant.maxResults)
            .enqueue(object:Callback<Playlist>{

                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                    if (response.isSuccessful){
                        data.value =response.body()
                    }
                }

                override fun onFailure(call: Call<Playlist>, t: Throwable) {
                    //404,401 -token,not found
                    //400-499

                }

            })
        return data
    }
}