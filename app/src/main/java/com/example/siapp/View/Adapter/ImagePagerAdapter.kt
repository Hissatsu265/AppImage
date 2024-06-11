package com.example.siapp.View.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.siapp.Model.Image
import com.example.siapp.R
class ImagePagerAdapter (private val context: Context, private val images: List<String>) : PagerAdapter() {

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_image_fullscreen, container, false)
        val imageView: ImageView = view.findViewById(R.id.imageView)

        val imageUrl = images[position]
        ViewCompat.setTransitionName(imageView, imageUrl)

        Glide.with(context)
            .load(images[position])
            .into(imageView)

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}