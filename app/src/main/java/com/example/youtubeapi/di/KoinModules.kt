package com.example.youtubeapi.di

import com.example.youtubeapi.core.network.networkModule

val koinModules = listOf(

    repoModules,
    viewModules,
    networkModule

)