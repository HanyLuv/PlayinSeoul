package com.work.hany.playinseoul.di;

import com.work.hany.playinseoul.main.MainActivity;
import com.work.hany.playinseoul.main.MainModule;
import com.work.hany.playinseoul.tourdetail.TourDetailActivity;
import com.work.hany.playinseoul.tourdetail.TourDetailPresenterModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();


    //TODO 이부분 없으니 에러남
    /**
     *      Caused by: java.lang.IllegalArgumentException: No injector factory bound for Class<com.work.hany.playinseoul.tourdetail.TourDetailActivity>
     * */
    @ActivityScoped
    @ContributesAndroidInjector(modules = TourDetailPresenterModule.class)
    abstract TourDetailActivity tourDetailActivity();

}
