package com.example.photoapplication.data.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.photoapplication.data.vos.PhotosVO
import com.example.photoapplication.utilites.EM_NULL_EVENT_RESPONSE
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


object PhotoModelImpl: PhotoModel, BaseModel() {

    override fun getPhotos(onFailure: (String) -> Unit): LiveData<List<PhotosVO>> {

        val databaseSingle = database.photoDao().getAllPhotosMaybe().subscribeOn(Schedulers.io())
            .flatMap {
                if (it.isEmpty()){
                    Maybe.empty()
                }else {
                    Maybe.just(it)
                }
            }

        val networkObservable = dataAgent.getPhotosObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                onFailure(it.message ?: EM_NULL_EVENT_RESPONSE)
            }
            .doOnNext {
                Log.d("AllPhotos network", it.toString())
            }

        Observable.concat(databaseSingle.toObservable(), networkObservable)
            .firstElement()
            .flatMap<LongArray> {
                database.photoDao().insertPhotos(it).subscribeOn(Schedulers.io()).toMaybe()
            }
            .subscribe(
                {
                    Log.d("Room", it.toString())
                },
                {
                    Log.e("Room", it.localizedMessage)
                }
            )

        val photosFromDB = database.photoDao().getAllPhotos()
        return Transformations.distinctUntilChanged(photosFromDB)

    }

    override fun getPhotoById(photoId: String): List<PhotosVO> {
        return database.photoDao().getPhotoById("%$photoId%")
    }

    override fun getPhotoDetail(id: String, onSuccess: (PhotosVO) -> Unit, onFailure: (String) -> Unit)
    {
        dataAgent.getPhotoDetail(id,
            onSuccess= {
                onSuccess(it)
            },
            onFailure= {
                onFailure(it)
            })
    }

}