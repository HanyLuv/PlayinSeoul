package com.work.hany.playinseoul.tourfavorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.work.hany.playinseoul.R
import com.work.hany.playinseoul.di.ActivityScoped
import com.work.hany.playinseoul.model.room.FavoriteAreaDatabase
import dagger.android.support.DaggerFragment
import javax.inject.Inject

@ActivityScoped
class FavoriteTourListFragment @Inject internal  constructor(): DaggerFragment(){

    @Inject
    lateinit var database: FavoriteAreaDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootView = inflater.inflate(R.layout.fragment_tour_list, null, false)
        database.favoriteAreaDao().getAll()
        return rootView

    }

}