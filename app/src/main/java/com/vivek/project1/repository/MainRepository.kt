package com.vivek.project1.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vivek.project1.models.AlbumResponseModelItem
import com.vivek.project1.network.WebService

class MainRepository(private val webService: WebService) {
    suspend fun getAlbumList(): LiveData<ArrayList<AlbumResponseModelItem>> {
        val data = MutableLiveData<ArrayList<AlbumResponseModelItem>>()
        val response = webService.getAlbumsList().await()
        data.value = response
        return data
    }
}