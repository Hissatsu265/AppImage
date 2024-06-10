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
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siapp.View.Adapter.ImageAdapter
import com.example.siapp.ViewModel.ImageViewModel
import com.example.siapp.ViewModel.MainViewModel
import com.example.siapp.ViewModel.MainViewModelFactory
import com.example.siapp.util.LoadingDialog
import android.view.WindowManager
class MainActivity2 : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(ImageRepository())
    }

    private lateinit var viewModelImage: ImageViewModel
    private lateinit var adapter: ImageAdapter

    private lateinit var recyclerView : RecyclerView
    private lateinit var searchView :SearchView
    private val loading = LoadingDialog(this)
    private var oldquery:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//====================================================================================================
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.purple_500)

//====================================================================================================
        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.search_view)

        adapter = ImageAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModelImage = ViewModelProvider(this).get(ImageViewModel::class.java)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                try {
                    if (query!=oldquery) {
                        oldquery=query.toString().trim()
                        adapter.submitList(emptyList())
                        viewModelImage.clearImages()
                        Query(query.toString().trim())
//                        setupRecyclerViewPagination(recyclerView)
                    }

                }catch (e:Exception){
                    Log.d("Tagloiiiiiiiii4",e.message.toString())
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        try {
            viewModelImage.images.observe(this, Observer {
                it?.let { newImages ->
                    adapter.submitList(newImages, clearExisting = true) // Clear existing items
                }
            })
        }
        catch (e:Exception){
            Log.d("TAGloiiiiiii5", "handleApiResponse: "+e.message.toString())
        }
//===================đặt scroll pagging=================================================================================

        setupRecyclerViewPagination(recyclerView)

//====================================================================================================

    }
    private fun Query(query:String){
        viewModel.searchImages(query).observe(this, Observer { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    loading.isDismiss()
                    resource.data?.let { handleApiResponse(it) }
                }
                Status.ERROR -> {
                    Log.d("MainActivity2", "Error: ${resource.message}")
                }
                Status.LOADING -> {
                    Log.d("MainActivity2", "Loading...")
                    loading.startLoading()
                }
            }
        })
    }
    private fun handleApiResponse(data: ApiResponse) {
        val images = data.images
        Log.d("TAGingi", "handleApiResponse: "+ data.searchParameters.toString())
        for (image in images) {
            Log.d("Image Info", "Title: ${image.title}, URL: ${image.imageUrl}")
        }
//=================================================================================
//        try {
            viewModelImage.setInitialImages(data.images)
//
//            viewModelImage.images.observe(this, Observer {
//                adapter.submitList(data.images,true)
//            })
//        }
//        catch (e:Exception){
//            Log.d("TAGloiiiiiii5", "handleApiResponse: "+e.message.toString())
//        }
    }
    private fun setupRecyclerViewPagination(recyclerView: RecyclerView) {
        try {
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.itemCount - 1) {
                        viewModelImage.loadMoreImages()
                    }
                }
            })
        }
        catch (e:Exception){
            Log.d("Tagloii1",e.message.toString())
        }
    }
    private fun ShowAlert(s:String){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show()
    }
}