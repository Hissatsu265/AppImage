package com.example.siapp.Model

data class ApiResponse(
    val searchParameters: SearchParameters,
    val images: List<Image>
)
