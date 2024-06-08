package com.example.siapp.Repository

import com.example.siapp.Model.ApiResponse
import com.example.siapp.network.ApiClient
import com.example.siapp.network.ApiService

class ImageRepository {
    private val apiService = ApiClient.retrofit.create(ApiService::class.java)

    suspend fun searchImages(query: String): ApiResponse {
        return apiService.searchImages(mapOf("q" to query))
    }
}