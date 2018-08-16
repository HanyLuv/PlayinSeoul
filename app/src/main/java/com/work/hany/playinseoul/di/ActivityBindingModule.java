package com.work.hany.playinseoul.di;

import com.work.hany.playinseoul.main.MainActivity;
import com.work.hany.playinseoul.main.MainModule;
import com.work.hany.playinseoul.tourdetail.DetailActivity;
import com.work.hany.playinseoul.tourdetail.DetailModule;
import com.work.hany.playinseoul.tourlist.TourListActivity;
import com.work.hany.playinseoul.tourlist.TourListFragment;
import com.work.hany.playinseoul.tourlist.TourModule;
import com.work.hany.playinseoul.toursearch.SearchActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();
//
    @ActivityScoped
    @ContributesAndroidInjector(modules = TourModule.class)
    abstract SearchActivity searchActivity();


    @ActivityScoped
    @ContributesAndroidInjector (modules = TourModule.class)
    abstract TourListActivity tourListActivity();


    @ActivityScoped
    @ContributesAndroidInjector(modules = DetailModule.class)
    abstract DetailActivity detailActivity();




}

