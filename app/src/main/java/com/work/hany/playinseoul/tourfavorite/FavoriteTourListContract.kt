package com.work.hany.playinseoul.tourfavorite

import com.work.hany.playinseoul.BasePresenter
import com.work.hany.playinseoul.BaseView
import com.work.hany.playinseoul.model.room.FavoriteAreaData


class FavoriteTourListContract {

    interface View : BaseView<Presenter> {
        fun initFavoriteTourListUi(favoriteTourList :List<FavoriteAreaData>)
    }


    interface Presenter : BasePresenter<View> {
        fun loadFavoriteTourList()
    }

}