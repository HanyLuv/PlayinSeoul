package com.work.hany.playinseoul.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.work.hany.playinseoul.model.room.FavoriteAreaDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
internal abstract class PlayInSeoulApplicationModule {
    //expose Application as an injectable context
    @Binds
    abstract fun bindContext(application: Application): Context

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideDatabase(application: Application): FavoriteAreaDatabase {
            return Room.databaseBuilder(application.applicationContext, FavoriteAreaDatabase::class.java, "favorite.db")
                    .allowMainThreadQueries()
                    .build()
        }
    }
}