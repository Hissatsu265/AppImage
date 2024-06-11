package com.example.siapp.View

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.ViewPager

import com.example.siapp.R
import android.content.Intent
import com.example.siapp.View.Adapter.ImagePagerAdapter
class Image_Info : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var openSourceButton: Button
    private var currentPosition: Int = 0
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
        viewPager = findViewById(R.id.viewPager)
        openSourceButton = findViewById(R.id.openSourceButton)

        val url_List: List<String>? = intent.getStringArrayListExtra("urlList")
        val img_List: List<String>? = intent.getStringArrayListExtra("imgList")

        currentPosition = intent.getIntExtra("position", 0)
        // Thiết lập ViewPager
        val adapter = img_List?.let { ImagePagerAdapter(this, it) }
        viewPager.adapter = adapter
        viewPager.currentItem = currentPosition

        // Thiết lập nút mở trang nguồn gốc
        openSourceButton.setOnClickListener {
            val url = url_List?.get(viewPager.currentItem)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}