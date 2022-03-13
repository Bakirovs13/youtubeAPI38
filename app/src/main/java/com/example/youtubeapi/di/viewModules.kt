package com.example.youtubeapi.di

import com.example.youtubeapi.ui.playlist.MyPlaylistViewModel
import com.example.youtubeapi.ui.playlistDetail.PlaylistDetailViewModel
import com.example.youtubeapi.ui.video.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules:Module = module {
    viewModel { MyPlaylistViewModel(get()) }
    viewModel { PlaylistDetailViewModel(get()) }
    viewModel { VideoViewModel(get()) }
}