package com.work.hany.playinseoul.tourlist

import com.work.hany.playinseoul.di.ActivityScoped
import com.work.hany.playinseoul.di.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector


@Module
abstract class TourModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun tourListFragment(): TourListFragment

//    @ActivityScoped
//    @Binds
//    abstract fun tourListPresenter(presenter: TourListPresenter): TourListContact.Presenter
//
//    @Provides
//    @ActivityScoped
//    fun provideTourContentType(activity: TourListActivity): Int {
//        return activity.intent.getIntExtra(TourListFragment.ArgumentKey.CONTENT_TYPE, 0)
//    }
}
