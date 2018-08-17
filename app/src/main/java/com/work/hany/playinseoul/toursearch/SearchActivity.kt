package com.work.hany.playinseoul.toursearch

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import com.work.hany.playinseoul.R
import com.work.hany.playinseoul.network.Area
import com.work.hany.playinseoul.util.ActivityUtils
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity: DaggerAppCompatActivity() {

    @Inject
    internal lateinit var searchFragment: Lazy<SearchFragment>


    interface OnSearchBarSelectedListener {
        fun onSearchBarSelected(position: Int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        var searchFragment = searchFragment.get()
        ActivityUtils.addFragmentToActivity(supportFragmentManager, searchFragment, R.id.contentFrame)
    }

    //TODO 사실 얘도 프레젠터 빼서 하는게 맞지만... 행위가 크지않아서 함수로빼서함
    fun updateSelectedTagUi(item: Area){
        searchSelectTagLinearLayout.post {
           var view = LayoutInflater.from(baseContext).inflate(R.layout.search_recycler_row_area_item,null)
            view.findViewById<TextView>(R.id.rowTitleTextView).text = item.name
            searchSelectTagLinearLayout.addView(view)
        }

    }

}