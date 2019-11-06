package com.example.photoapplication.persistences.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.photoapplication.data.vos.PhotosVO
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
abstract class PhotoDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    abstract fun insertPhotos(events: List<PhotosVO>): Single<LongArray>

    @Query("SELECT * FROM photos")
    abstract fun getAllPhotos(): LiveData<List<PhotosVO>>


    @Query("SELECT * FROM photos")
    abstract fun getAllPhotosMaybe(): Maybe<List<PhotosVO>>

    @Query("SELECT * FROM photos WHERE id LIKE :id")
    abstract fun getPhotoById(id: String): List<PhotosVO>

}