package com.example.photoapplication.data.vos

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "photos")
data class PhotosVO (

    @PrimaryKey
    @SerializedName("id")
    var id: String,

    @SerializedName("width")
    var width: Int,

    @SerializedName("height")
    var height: Int,

    @SerializedName("urls")
    @Embedded(prefix = "url_")
    var urls: PhotoUrlVO,

    @SerializedName("user")
    @Embedded(prefix = "user_")
    var userVO: UserVO
){
    val heightRatio: Double
        get() = height.toDouble() / width.toDouble()

}
