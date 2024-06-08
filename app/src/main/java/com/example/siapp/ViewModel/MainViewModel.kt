package com.example.siapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.siapp.Repository.ImageRepository
import com.example.siapp.util.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repository: ImageRepository) : ViewModel() {

    fun searchImages(query: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val response = repository.searchImages(query)
            emit(Resource.success(response))
        } catch (exception: Exception) {
            emit(Resource.error(null, exception.message ?: "Error Occurred!"))
        }
    }
}