package com.example.photoapplication.networks.dataagents

import com.example.photoapplication.data.vos.PhotosVO
import io.reactivex.Observable

interface PhotoDataAgent {

    fun getPhotos(
        onSuccess: (List<PhotosVO>) -> Unit,
        onFailure: (String) -> Unit)

    fun getPhotosObservable(): Observable<List<PhotosVO>>

    fun getPhotoDetail(
        id: String,
        onSuccess: (PhotosVO) -> Unit,
        onFailure: (String) -> Unit
    )

}