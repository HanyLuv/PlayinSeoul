package com.work.hany.playinseoul.tourfavorite

import android.os.Bundle
import com.work.hany.playinseoul.R
import com.work.hany.playinseoul.model.room.FavoriteAreaDatabase
import com.work.hany.playinseoul.tourlist.TourListFragment
import com.work.hany.playinseoul.util.ActivityUtils
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class FavoriteTourListActivity: DaggerAppCompatActivity(){
    @Inject
    internal lateinit var favoriteTourListFragment: Lazy<FavoriteTourListFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
        var favoriteTourListFragment = favoriteTourListFragment.get()
        ActivityUtils.addFragmentToActivity(supportFragmentManager, favoriteTourListFragment, R.id.contentFrame)

    }
}