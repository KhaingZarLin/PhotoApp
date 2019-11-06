package com.example.photoapplication.utilites

import com.example.photoapplication.BuildConfig

const val BASE_URL = "https://api.unsplash.com/photos/"
const val ACCESS_KEY = "f946242f2447704aff3bf5838cf30cae9e2d7cbed1d818e2525a29959710fdcd"

//const val Photo_API_KEY = BuildConfig.Photo_API_KEY


// Error Messages
const val EM_NULL_EVENT_RESPONSE = "Please Check Internet Connection"
const val EM_EVENT_MODEL_NOT_INITIALIZED = "Event Model should have been initialized"
const val EM_UNKNOWN_ERROR = "Unexpected Error"

// DB Name
const val PHOTO_DB = "photoDB"
const val GET_Photo="?client_id=$ACCESS_KEY"
const val GET_PHOTO_DETAIL="{id}/?client_id=$ACCESS_KEY"



