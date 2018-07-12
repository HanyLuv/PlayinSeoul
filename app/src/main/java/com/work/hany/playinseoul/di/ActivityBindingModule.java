package com.work.hany.playinseoul.di;

import com.work.hany.playinseoul.main.MainActivity;
import com.work.hany.playinseoul.main.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();

}
