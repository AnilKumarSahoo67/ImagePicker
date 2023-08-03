package com.example.imagepicker

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ImageViewAdapter(private val imageList : ArrayList<Uri?>, private val context : Context) : RecyclerView.Adapter<ImageViewAdapter.MyImageViewHolder>() {



    inner class MyImageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val imageView : ImageView = itemView.findViewById(R.id.imageView)
        fun bind(position: Int) {
            imageList.size.let {
               imageView.setImageURI(imageList[position])
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyImageViewHolder {
        return MyImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_holder, parent, false))
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: MyImageViewHolder, position: Int) {
       holder.bind(position)
    }
}