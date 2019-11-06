package com.example.photoapplication.mvp.presenter

import androidx.lifecycle.ViewModel
import com.example.photoapplication.mvp.views.BaseView

abstract class BasePresenter<T: BaseView> : ViewModel() {

    protected lateinit var mView: T

    open fun initPresenter(view: T){
        this.mView  = view
    }
}