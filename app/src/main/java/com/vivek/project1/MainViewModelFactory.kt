package com.vivek.project1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vivek.project1.repository.MainRepository
import com.vivek.project1.viewModel.MainViewModel

class MainViewModelFactory(private val repository: MainRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}