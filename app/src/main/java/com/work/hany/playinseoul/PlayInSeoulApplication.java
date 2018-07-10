package com.work.hany.playinseoul;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class PlayInSeoulApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .application(this)
                .build();
    }
}
