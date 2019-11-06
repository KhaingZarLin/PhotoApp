package com.example.photoapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.photoapplication.R
import com.example.photoapplication.data.vos.PhotosVO
import com.example.photoapplication.delegate.PhotoItemDelegate
import com.example.photoapplication.viewholders.PhotoViewHolder

class PhotoListAdapter(private val delegate: PhotoItemDelegate) : BaseAdapter<PhotoViewHolder, PhotosVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return PhotoViewHolder(view, delegate)
    }

}