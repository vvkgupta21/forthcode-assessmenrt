package com.vivek.project1.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vivek.project1.models.AlbumResponseModelItem
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val baseUrl = "https://jsonplaceholder.typicode.com"

private val retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

interface WebService {
    @GET("/photos")
    fun getAlbumsList(): Deferred<ArrayList<AlbumResponseModelItem>>
}

object JsonTestApi {
    val webService: WebService by lazy {
        retrofit.create(WebService::class.java)
    }
}