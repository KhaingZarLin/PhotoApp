package com.example.photoapplication.mvp.presenter

import com.example.photoapplication.BaseActivity
import com.example.photoapplication.data.models.PhotoModelImpl
import com.example.photoapplication.mvp.views.PhotoDetailView

class PhotoDetailListPresenter : BasePresenter<PhotoDetailView>() {

    fun onUiReady(activity: BaseActivity, photoId: String) {
        if (photoId != null) {
            val model = PhotoModelImpl
            model.getPhotoDetail(photoId, {
                mView.displayPhotoDetail(it)
            }, {
                mView.errorMessage(it)
            })
        }
    }

}