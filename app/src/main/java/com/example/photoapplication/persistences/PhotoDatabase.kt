package com.example.photoapplication.persistences

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.photoapplication.data.vos.PhotosVO
import com.example.photoapplication.persistences.daos.PhotoDao
import com.example.photoapplication.utilites.PHOTO_DB

@Database(entities = [PhotosVO::class], exportSchema = false, version = 5)
abstract class PhotoDatabase: RoomDatabase() {

    abstract fun photoDao(): PhotoDao

    companion object {
        private var instance: PhotoDatabase? = null

        fun getInstance(context: Context): PhotoDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, PhotoDatabase::class.java, PHOTO_DB)
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance!!
        }
    }
}