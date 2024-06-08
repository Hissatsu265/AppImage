package com.example.siapp.network

import com.example.siapp.Model.ApiResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json", "X-API-KEY: 889c5acc82d4ae66bfffbe5ac852d708c493e3e9")
    @POST("images")
    suspend fun searchImages(@Body requestBody: Map<String, String>): ApiResponse
}