package com.work.hany.playinseoul.tourfavorite

import com.work.hany.playinseoul.di.ActivityScoped
import com.work.hany.playinseoul.di.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FavoriteTourListModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun favoriteTourListModule(): FavoriteTourListModule

    @ActivityScoped
    @Binds
    abstract fun favoriteTourListPresenter(presenter: FavoriteTourListPresenter): FavoriteTourListContract.Presenter

}