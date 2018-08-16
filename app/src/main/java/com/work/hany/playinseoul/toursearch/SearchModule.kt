package com.work.hany.playinseoul.toursearch

import com.work.hany.playinseoul.di.ActivityScoped
import com.work.hany.playinseoul.di.FragmentScoped
import com.work.hany.playinseoul.tourlist.TourListContact
import com.work.hany.playinseoul.tourlist.TourListFragment
import com.work.hany.playinseoul.tourlist.TourListPresenter
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun searchFragment(): SearchFragment

    @ActivityScoped
    @Binds
    abstract fun searchPresenter(presenter: SearchPresenter): SearchContact.Presenter


}