package com.example.siapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.siapp.Repository.CacheRepository


class ViewModelCacheFactory (private val cacheRepository: CacheRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelCache::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewModelCache(cacheRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
