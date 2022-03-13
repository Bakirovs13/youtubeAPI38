package com.example.youtubeapi.core.network

import com.example.youtubeapi.BuildConfig.BASE_URL
import com.example.youtubeapi.data.remote.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {

    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    factory { provideApi(get()) }
}

fun provideOkHttpClient(): OkHttpClient {

    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    return  OkHttpClient.Builder()   //internet connection check
        .writeTimeout(20,TimeUnit.SECONDS)
        .readTimeout(20,TimeUnit.SECONDS)
        .connectTimeout(20,TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

}

 fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

    return Retrofit.Builder()
         .baseUrl(BASE_URL)
         .addConverterFactory(GsonConverterFactory.create())
         .client(okHttpClient)
         .build()

 }

fun provideApi(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)

}