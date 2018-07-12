package com.work.hany.playinseoul.main;

import com.work.hany.playinseoul.di.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract MainFragment mainFragment();
//
//    @ActivityScoped
//    @Binds
//    abstract TasksContract.Presenter taskPresenter(TasksPresenter presenter);
}
