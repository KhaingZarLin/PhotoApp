package com.example.photoapplication.networks

import com.example.photoapplication.data.vos.PhotosVO
import com.example.photoapplication.utilites.BASE_URL
import com.example.photoapplication.utilites.GET_PHOTO_DETAIL
import com.example.photoapplication.utilites.GET_Photo
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotosApi {

    @GET(GET_Photo)
    fun getAllPhotos(): Call<List<PhotosVO>>

    @GET(GET_Photo)
    fun getAllPhotosObservable(): Observable<List<PhotosVO>>

    @GET(GET_PHOTO_DETAIL)
    fun getPhotoDetail(@Path("id") id: String): Call<PhotosVO>
}