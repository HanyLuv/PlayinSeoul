package com.work.hany.playinseoul.model.repository

import com.work.hany.playinseoul.model.room.FavoriteAreaData


// 확장성을 위해 로컬과 네트워크용으로 2개 구현..
interface TourDataSource {

    interface LoadFavoriteTourListCallBack {
         fun onTasksLoaded(tasks: List<FavoriteAreaData>)
         fun onDataNotAvailable()
    }


}