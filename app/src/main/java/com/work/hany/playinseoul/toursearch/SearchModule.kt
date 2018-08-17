package com.work.hany.playinseoul.toursearch

import com.work.hany.playinseoul.di.ActivityScoped
import com.work.hany.playinseoul.di.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract  class SearchModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun searchFragment(): SearchFragment

    @ActivityScoped
    @Binds
    abstract fun searchPresenter(presenter: SearchPresenter): SearchContact.Presenter


}