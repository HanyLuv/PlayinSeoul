package com.work.hany.playinseoul

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class YogijogiApplication : DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
       return DaggerYogijogiAppComponent.builder().application(this).build()
    }

}