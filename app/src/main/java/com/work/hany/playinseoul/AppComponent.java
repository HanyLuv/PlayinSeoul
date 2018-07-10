package com.work.hany.playinseoul;


import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent extends AndroidInjector<PlayInSeoulApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(Application application);
        AppComponent build();
    }
}
