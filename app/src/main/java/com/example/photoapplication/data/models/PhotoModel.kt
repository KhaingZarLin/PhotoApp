package com.example.photoapplication.data.models

import androidx.lifecycle.LiveData
import com.example.photoapplication.data.vos.PhotosVO


interface PhotoModel {

    fun getPhotos(
        onFailure: (String) -> Unit
    ) : LiveData<List<PhotosVO>>

    fun getPhotoById(photoId: String): List<PhotosVO>


    fun getPhotoDetail(
        id: String,
        onSuccess: (PhotosVO) -> Unit,
        onFailure: (String) -> Unit
    )



}