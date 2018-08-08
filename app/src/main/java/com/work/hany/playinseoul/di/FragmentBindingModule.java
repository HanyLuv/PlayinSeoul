package com.work.hany.playinseoul.di;

import com.work.hany.playinseoul.common.OverViewDetailFragment;
import com.work.hany.playinseoul.main.MainActivity;
import com.work.hany.playinseoul.main.MainModule;
import com.work.hany.playinseoul.tourdetail.DetailActivity;
import com.work.hany.playinseoul.tourdetail.DetailModule;
import com.work.hany.playinseoul.tourdetail.OverViewModule;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {
//    @FragmentScoped
//    @ContributesAndroidInjector(modules = OverViewModule.class)
//    abstract OverViewDetailFragment overViewDetailFragment();
@FragmentScoped
@ContributesAndroidInjector(modules = OverViewModule.class)
abstract OverViewDetailFragment overViewDetailFragment();
}
