package com.work.hany.playinseoul.model.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import retrofit2.Retrofit

@Database(entities = arrayOf(FavoriteAreaData::class), version = 1)
abstract class FavoriteAreaDatabase: RoomDatabase(){

    abstract fun favoriteAreaDao(): FavoriteAreaDao

    companion object {
        private var INSTANCE: FavoriteAreaDatabase? = null

        fun getInstance(context: Context): FavoriteAreaDatabase? {
            if (INSTANCE == null) {
                synchronized(FavoriteAreaData::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, FavoriteAreaDatabase::class.java, "favorite.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}