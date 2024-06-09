package com.example.siapp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siapp.Model.Image
import kotlinx.coroutines.launch

class ImageViewModel : ViewModel() {
    private val _images = MutableLiveData<List<Image>>()
    val images: LiveData<List<Image>> get() = _images

    private var currentPage = 0
    private val pageSize = 3
    private var allImages: List<Image> = emptyList()
    private var isLoading = false
    fun setInitialImages(initialImages: List<Image>) {
        currentPage = 0
        allImages = emptyList()
        _images.value = emptyList()
        allImages = initialImages

        loadMoreImages()
    }

    fun loadMoreImages() {
        try {
            if (isLoading) return
            isLoading=true
            val startIndex = currentPage * pageSize
            val endIndex = startIndex + pageSize
            if (startIndex < allImages.size) {
                viewModelScope.launch {
                    val nextPageImages =
                        allImages.subList(startIndex, endIndex.coerceAtMost(allImages.size))
                    currentPage++
                    _images.value = _images.value.orEmpty() + nextPageImages
                    Log.d("TAGhhhhhhh", "loadMoreImages: "+ currentPage)
                    Log.d("TAGhhhhhhh1", "loadMoreImages: "+ startIndex)
                    Log.d("TAGhhhhhhh2", "loadMoreImages: "+ _images.value.toString())
                    Log.d("TAGhhhhhhh3", "loadMoreImages: "+ _images.value!!.size.toString())
                }
            }
            isLoading=false
        }
        catch (e:Exception){
            Log.d("TAGloiii", "loadMoreImages: "+e.message.toString())
            isLoading=false
        }

    }
    fun clearImages() {
        try {
            _images.value = emptyList()
            allImages = emptyList()
            currentPage = 0
            isLoading = false
        }catch (e:Exception){
            Log.d("TAGloiiiiiii3", "clearImages: "+e.message.toString())
        }

    }
}
