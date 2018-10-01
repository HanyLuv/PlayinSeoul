package com.work.hany.playinseoul

import com.work.hany.playinseoul.model.room.FavoriteAreaDatabase
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class YogijogiApplication : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
       return DaggerYogijogiAppComponent.builder().application(this).build()
    }

}