package com.example.photoapplication.data.models

import android.content.Context
import com.example.photoapplication.networks.dataagents.PhotoDataAgent
import com.example.photoapplication.networks.dataagents.RetrofitAgent
import com.example.photoapplication.persistences.PhotoDatabase

abstract class BaseModel{

    protected val dataAgent: PhotoDataAgent = RetrofitAgent

    protected lateinit var database: PhotoDatabase

    fun initDatabase(context: Context){
        database = PhotoDatabase.getInstance(context)
    }
}