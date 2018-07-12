package com.work.hany.playinseoul;

import android.app.Application;

import com.work.hany.playinseoul.di.ActivityBindingModule;
import com.work.hany.playinseoul.di.PlayInSeoulApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        PlayInSeoulApplicationModule.class,
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
