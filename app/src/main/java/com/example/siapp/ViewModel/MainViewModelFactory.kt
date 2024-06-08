package com.example.siapp.ViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.siapp.Repository.ImageRepository

class MainViewModelFactory(private val repository: ImageRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}