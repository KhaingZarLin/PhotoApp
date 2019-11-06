package com.example.photoapplication.mvp.presenter

import androidx.lifecycle.Observer
import com.example.photoapplication.BaseActivity
import com.example.photoapplication.data.models.PhotoModelImpl
import com.example.photoapplication.delegate.PhotoItemDelegate
import com.example.photoapplication.mvp.views.PhotoListView

class PhotoListPresenter: BasePresenter<PhotoListView>(), PhotoItemDelegate {
    override fun onTapPhotoItem(photoId: String) {
        mView.navigateToDetail(photoId)
    }


    fun onUiReady(activity: BaseActivity){
        val model = PhotoModelImpl

        model.getPhotos { mView.displayError(it) }
            .observe(activity, Observer {
                mView.displayPhotoList(it)
            })
    }
}