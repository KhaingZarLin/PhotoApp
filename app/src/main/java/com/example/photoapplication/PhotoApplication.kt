package com.example.photoapplication

import android.app.Application
import com.example.photoapplication.data.models.PhotoModelImpl

class PhotoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        PhotoModelImpl.initDatabase(applicationContext)
    }
    }