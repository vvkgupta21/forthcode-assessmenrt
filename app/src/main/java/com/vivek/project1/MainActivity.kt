package com.vivek.project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vivek.project1.adapter.AlbumAdapter
import com.vivek.project1.databinding.ActivityMainBinding
import com.vivek.project1.network.JsonTestApi
import com.vivek.project1.network.StateData
import com.vivek.project1.repository.MainRepository
import com.vivek.project1.viewModel.MainViewModel

const val TAG = "JsonTest"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val repository = MainRepository(JsonTestApi.webService)
        viewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
        initAdapter()
        getAllAlbums()
    }

    private fun initAdapter(){
        adapter = AlbumAdapter(arrayListOf())
        binding.rv.adapter = adapter
    }

    private fun getAllAlbums(){
        viewModel.getAlbumList().observe(this, Observer {
            it?.let { stateDate ->
                when(stateDate.status){
                    StateData.DataStatus.ERROR -> {
                        Log.d(TAG, "getAllAlbums: ${stateDate.error?.message}")
                    }
                    StateData.DataStatus.LOADING -> {
                        Log.d(TAG, "getAllAlbums: Loading")
                    }
                    StateData.DataStatus.SUCCESS -> {
                        Log.d(TAG, "getAllAlbums: ${stateDate.data}")
                        stateDate.data?.let { it1 -> adapter.initList(it1) }
                    }
                    else -> {}
                }
            }
        })
    }
}