package com.example.imagepicker

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var imageList = arrayListOf<Uri?>()
    private lateinit var imageViewAdapter: ImageViewAdapter
    private var recyclerView : RecyclerView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        findViewById<Button>(R.id.pickImage).setOnClickListener {
//            val intent = Intent()
//            intent.action = Intent.ACTION_GET_CONTENT
//            intent.type = "image/*"
//            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            requestImagePick.launch("image/*")
        }

    }


    private val requestImagePick = registerForActivityResult(ActivityResultContracts.GetMultipleContents()){
        Log.e("TAG", "Image URI : $it" )
        it?.let {
            for (element in it){
                imageList.add(element)
            }
        }
        if (imageList.size>0){
            imageViewAdapter = ImageViewAdapter(imageList = imageList,this)
            recyclerView?.visibility = View.VISIBLE
            recyclerView?.adapter = imageViewAdapter
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            data?.let {
                val  count :Int = it.clipData?.itemCount ?: 0
                if (count >0) {
                    for (i in 0 until count) {
                        imageList.add(it.clipData!!.getItemAt(i).uri)
                    }
                }else{
                    imageList.add(it.data)
                }
            }
            Log.e("TAG", "onActivityResult: $imageList", )
        }
    }
}