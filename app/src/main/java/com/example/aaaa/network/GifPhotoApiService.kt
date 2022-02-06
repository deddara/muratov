package com.example.giphloaderapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private const val BASE_URL =
    "http://developerslife.ru"

private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object GifApi {
    val retrofitService : GifPhotoApiService by lazy {
        retrofit.create(GifPhotoApiService::class.java) }
}

interface GifPhotoApiService {
    @GET("latest/{id}")
    suspend fun getLatestPhotos(@Path("id") pageId: Int, @Query("json") query: String) : GifResult

    @GET("hot/{id}")
    suspend fun getHotPhotos(@Path("id") pageId: Int, @Query("json") query: String) : GifResult

    @GET("top/{id}")
    suspend fun getTopPhotos(@Path("id") pageId: Int, @Query("json") query: String) : GifResult
}

