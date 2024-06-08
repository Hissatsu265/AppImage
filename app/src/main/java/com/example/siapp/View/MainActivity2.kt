package com.example.siapp.View

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.siapp.R


import androidx.activity.viewModels

import androidx.lifecycle.Observer

import com.example.siapp.Model.ApiResponse
import com.example.siapp.Repository.ImageRepository
import com.example.siapp.util.Status

import android.util.Log
import com.example.siapp.ViewModel.MainViewModel
import com.example.siapp.ViewModel.MainViewModelFactory

class MainActivity2 : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(ImageRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel.searchImages("apple inc").observe(this, Observer { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let { handleApiResponse(it) }
                }
                Status.ERROR -> {
                    Log.d("MainActivity", "Error: ${resource.message}")
                }
                Status.LOADING -> {
                    Log.d("MainActivity", "Loading...")
                }
            }
        })
    }

    private fun handleApiResponse(data: ApiResponse) {
        val images = data.images
        for (image in images) {
            Log.d("Image Info", "Title: ${image.title}, URL: ${image.imageUrl}")
        }
    }
}