package com.vivek.project1.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.vivek.project1.models.AlbumResponseModelItem
import com.vivek.project1.network.StateLiveData
import com.vivek.project1.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

const val TAG = "JsonTest"

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    lateinit var viewModelJob: Job

    fun getAlbumList(): StateLiveData<ArrayList<AlbumResponseModelItem>> {
        val data = StateLiveData<ArrayList<AlbumResponseModelItem>>()
        data.postLoading()
        try {
            viewModelJob = CoroutineScope(Job() + Dispatchers.Main).launch {
                val response = repository.getAlbumList()
                response.value?.let {
                    data.postSuccess(it)
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "getAlbumList: ${e.message}")
        }
        return data
    }

}