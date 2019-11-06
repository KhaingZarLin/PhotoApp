package com.example.photoapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.photoapplication.data.models.PhotoModel
import com.example.photoapplication.data.models.PhotoModelImpl

abstract class BaseActivity: AppCompatActivity() {

    protected lateinit var model: PhotoModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = PhotoModelImpl
    }

}