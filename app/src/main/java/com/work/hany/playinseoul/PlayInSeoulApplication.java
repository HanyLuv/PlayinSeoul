package com.work.hany.playinseoul;

import com.google.android.gms.maps.MapsInitializer;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class PlayInSeoulApplication  extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerPlayInSeoulAppComponent.builder().application(this).build();
    }
}
