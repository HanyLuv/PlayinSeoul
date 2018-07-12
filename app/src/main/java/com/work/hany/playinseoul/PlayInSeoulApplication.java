package com.work.hany.playinseoul;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class PlayInSeoulApplication  extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerPlayInSeoulAppComponent.builder().application(this).build();
    }
}
