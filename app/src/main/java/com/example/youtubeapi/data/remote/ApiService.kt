package com.example.youtubeapi.data.remote

import com.example.youtubeapi.data.models.Playlist
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
   suspend fun getPlaylists(
        @Query("part")part:String,
        @Query("channelId")channelId:String,
        @Query("key")apiKey:String,
        @Query("maxResults")maxResult:Int
    ):Response<Playlist>   //callback - listener , response - class for  coroutines


    @GET("playlistItems")
    suspend fun getPlaylistItems(
        @Query("part") part: String,
        @Query("playlistId") channelId: String,
        @Query("key") key: String,
        @Query("maxResults") maxResults: Int,
        @Query("pageToken") pageToken: String?
    ): Response<Playlist>

    @GET("videos")
    suspend fun getVideos(
        @Query("part")part:String,
        @Query("id")id:String,
        @Query("key")key:String
    ):Response<Playlist>
}