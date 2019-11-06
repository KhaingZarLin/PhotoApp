package com.example.photoapplication.mvp.views

import com.example.photoapplication.data.vos.PhotosVO

interface PhotoDetailView : BaseView{
    fun displayPhotoDetail(photoData: PhotosVO)
    fun errorMessage(errorMessage: String)
}