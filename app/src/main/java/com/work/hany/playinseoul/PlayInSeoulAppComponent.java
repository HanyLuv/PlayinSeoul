package com.work.hany.playinseoul;

import android.app.Application;

import com.work.hany.playinseoul.di.ActivityBindingModule;
import com.work.hany.playinseoul.di.FragmentBindingModule;
import com.work.hany.playinseoul.di.PlayInSeoulApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


//https://blog.csdn.net/u010046451/article/details/80614979 해보기 
@Singleton
@Component(modules = {
        PlayInSeoulApplicationModule.class,
        FragmentBindingModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class })
public interface PlayInSeoulAppComponent extends AndroidInjector<PlayInSeoulApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        PlayInSeoulAppComponent.Builder application(Application application);
        PlayInSeoulAppComponent build();

    }
}
