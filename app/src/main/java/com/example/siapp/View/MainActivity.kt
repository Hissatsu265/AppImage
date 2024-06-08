package com.example.siapp.View

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.siapp.Model.ApiResponse
import com.example.siapp.R

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val client = OkHttpClient.Builder().build()

        val mediaType = "application/json".toMediaTypeOrNull()
        val body = RequestBody.create(mediaType, "{\"q\":\"apple inc\"}")

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val request = Request.Builder()
                    .url("https://google.serper.dev/images")
                    .post(body)
                    .addHeader("X-API-KEY", "889c5acc82d4ae66bfffbe5ac852d708c493e3e9")
                    .addHeader("Content-Type", "application/json")
                    .build()
                val response: Response = client.newCall(request).execute()

                val responseBody = response.body?.string()
                Log.d("TAGhhh", "Response Body: " + responseBody)

                responseBody?.let {
                    val gson = Gson()
                    val data = gson.fromJson(it, ApiResponse::class.java)
                    Log.d("TAGhhh", "Parsed Response Data: $data")

                    handleApiResponse(data)
                }

            } catch (e: Exception) {
                Log.d("hhhhhhl", "Exception: " + e.message.toString())
            }
        }
    }

    private fun handleApiResponse(data: ApiResponse) {
        val images = data.images
        for (image in images) {
            Log.d("Image Info", "Title: ${image.title}, URL: ${image.imageUrl}")
        }
    }
}