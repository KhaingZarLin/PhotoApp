package com.example.photoapplication.networks.dataagents

import com.example.photoapplication.data.vos.PhotosVO
import com.example.photoapplication.networks.PhotosApi
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.example.photoapplication.utilites.BASE_URL
import com.example.photoapplication.utilites.EM_NULL_EVENT_RESPONSE
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit

object RetrofitAgent: PhotoDataAgent {

    private val photoApi: PhotosApi

    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        photoApi = retrofit.create(PhotosApi::class.java)
    }

    override fun getPhotos(onSuccess: (List<PhotosVO>) -> Unit, onFailure: (String) -> Unit) {

        val call = photoApi.getAllPhotos()
        call.enqueue(object: Callback<List<PhotosVO>>{
            override fun onFailure(call: Call<List<PhotosVO>>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(call: Call<List<PhotosVO>>, response: Response<List<PhotosVO>>) {
                val response = response.body()
                if (response != null){
                    onSuccess(response)
                }else{
                    onFailure(EM_NULL_EVENT_RESPONSE)
                }
            }
        })
    }

    override fun getPhotosObservable(): Observable<List<PhotosVO>> {
        return photoApi.getAllPhotosObservable()
            .flatMap<List<PhotosVO>>{
                val data = it
                if (data == null){
                    Observable.error(RuntimeException(EM_NULL_EVENT_RESPONSE))
                }else {
                    Observable.just(data)
                }
            }
    }

    override fun getPhotoDetail(id: String, onSuccess: (PhotosVO) -> Unit, onFailure: (String) -> Unit) {
        val call = photoApi.getPhotoDetail(id)
        call.enqueue(object: Callback<PhotosVO>{
            override fun onFailure(call: Call<PhotosVO>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(call: Call<PhotosVO>, response: Response<PhotosVO>) {
                val photoDetail = response.body()
                if (photoDetail != null){
                    onSuccess(photoDetail)
                }else{
                    onFailure(EM_NULL_EVENT_RESPONSE)
                }
            }

        })
    }

}