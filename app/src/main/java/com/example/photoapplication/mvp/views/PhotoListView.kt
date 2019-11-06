package com.example.photoapplication.mvp.views

import com.example.photoapplication.data.vos.PhotosVO


interface PhotoListView : BaseView{
    fun displayPhotoList(photoList: List<PhotosVO>)
    fun displayError(errorMessage: String)
    fun navigateToDetail(photoId: String)
}