package com.example.siapp.View.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.siapp.Model.ApiResponse
import com.example.siapp.Model.Image
import com.example.siapp.R
class ImageAdapter (private val onItemClick: (Int, ImageView) -> Unit)
    : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private val images = mutableListOf<Image>()

    fun submitList(newImages: List<Image>, clearExisting: Boolean = false) {
        if (clearExisting) {
            images.clear()
            notifyDataSetChanged()
        }
        val oldSize = images.size
        images.addAll(newImages)
        notifyItemRangeInserted(oldSize, newImages.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view,onItemClick)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    class ImageViewHolder(itemView: View,private val onItemClick: (Int, ImageView) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.titleView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(image: Image) {
            try {
                title.text = image.title
                Glide.with(itemView.context)
                    .load(image.imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView)
            }
            catch (e:Exception){
                Log.d("Tagloii2",e.message.toString())
            }
            ViewCompat.setTransitionName(imageView, image.imageUrl)
            itemView.setOnClickListener {
                onItemClick(adapterPosition,imageView)
            }

        }
    }
}
