package com.work.hany.playinseoul.model.repository

import com.work.hany.playinseoul.model.room.FavoriteAreaDao
import com.work.hany.playinseoul.util.AppExecutors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TourLocalDataSource @Inject constructor(
        private val favoriteAreaDao: FavoriteAreaDao,
        private val appExecutors: AppExecutors) : TourDataSource {


}