package com.work.hany.playinseoul.toursearch

import android.os.Bundle
import com.work.hany.playinseoul.R
import com.work.hany.playinseoul.util.ActivityUtils
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SearchActivity: DaggerAppCompatActivity() {
    @Inject
    internal lateinit var tourSearchFragment: Lazy<SearchFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var searchFragment = tourSearchFragment.get()
        ActivityUtils.addFragmentToActivity(supportFragmentManager, searchFragment, R.id.contentFrame)
    }

}