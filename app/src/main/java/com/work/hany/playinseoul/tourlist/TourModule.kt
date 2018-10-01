package com.work.hany.playinseoul.tourlist

import com.work.hany.playinseoul.di.ActivityScoped
import com.work.hany.playinseoul.di.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector


@Module
internal abstract class TourModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun tourListFragment(): TourListFragment

    @ActivityScoped
    @Binds
    abstract fun tourListPresenter(presenter: TourListPresenter): TourListContract.Presenter


    @Module
    companion object { //액티비티에서 보내주는 타입을 Fragment 에서 받으려고 한듯..
        @JvmStatic @Provides @ActivityScoped fun provideTourContentType(activity: TourListActivity): Int {
            return activity.intent.getIntExtra(TourListFragment.ArgumentKey.CONTENT_TYPE, 0)
        }
    }

}
