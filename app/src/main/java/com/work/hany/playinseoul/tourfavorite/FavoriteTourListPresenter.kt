package com.work.hany.playinseoul.tourfavorite

import com.work.hany.playinseoul.tourlist.TourListContract
import javax.inject.Inject

class FavoriteTourListPresenter @Inject constructor(): FavoriteTourListContract.Presenter {
    lateinit var tourListView: TourListContract.View


    override fun loadFavoriteTourList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun takeView(view: FavoriteTourListContract.View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dropView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}