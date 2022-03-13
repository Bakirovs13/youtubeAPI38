package com.example.youtubeapi.di

import com.example.youtubeapi.repositories.Repository
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModules:Module = module {
    single {Repository(get())}
}