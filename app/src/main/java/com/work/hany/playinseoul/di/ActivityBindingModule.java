package com.work.hany.playinseoul.di;

import com.work.hany.playinseoul.main.MainActivity;
import com.work.hany.playinseoul.main.MainModule;
import com.work.hany.playinseoul.tourdetail.DetailActivity;
import com.work.hany.playinseoul.tourdetail.DetailModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();


    //TODO 이부분 없으니 에러남
    /**
     *      Caused by: java.lang.IllegalArgumentException: No injector factory bound for Class<com.work.hany.playinseoul.tourdetail.DetailActivity>
     * */
    @ActivityScoped
    @ContributesAndroidInjector(modules = DetailModule.class)
    abstract DetailActivity tourDetailActivity();

}
