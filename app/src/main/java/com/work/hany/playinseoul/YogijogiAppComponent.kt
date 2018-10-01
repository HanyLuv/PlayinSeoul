package com.work.hany.playinseoul

import android.app.Application
import com.work.hany.playinseoul.di.ActivityBindingModule
import com.work.hany.playinseoul.di.PlayInSeoulApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [PlayInSeoulApplicationModule::class, ActivityBindingModule::class, AndroidSupportInjectionModule::class])
internal interface YogijogiAppComponent : AndroidInjector<YogijogiApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): YogijogiAppComponent.Builder
        fun build(): YogijogiAppComponent
    }

}