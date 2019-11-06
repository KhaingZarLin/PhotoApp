package com.example.photoapplication.viewholders

import android.view.View
import coil.api.load
import com.example.photoapplication.components.DynamicImageView
import com.example.photoapplication.data.vos.PhotosVO
import com.example.photoapplication.delegate.PhotoItemDelegate
import kotlinx.android.synthetic.main.item_view.view.*

class PhotoViewHolder(itemView: View, private val delegate: PhotoItemDelegate) : BaseViewHolder<PhotosVO>(itemView) {

    private val photoView: DynamicImageView = itemView.photo_iv

    init {

        itemView.setOnClickListener {
            data?.id?.let { id ->
                delegate.onTapPhotoItem(id)
            }
        }
    }

    override fun bindData(data: PhotosVO) {
        photoView.heightRatio = data.heightRatio
        photoView.load(data.urls.regular)

    }



}