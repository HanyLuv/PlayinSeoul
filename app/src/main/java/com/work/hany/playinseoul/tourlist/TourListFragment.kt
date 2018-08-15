package com.work.hany.playinseoul.tourlist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.work.hany.playinseoul.R
import com.work.hany.playinseoul.di.ActivityScoped
import com.work.hany.playinseoul.network.AreaTour
import com.work.hany.playinseoul.tourlist.adapter.TourListRecyclerViewAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_tour_list.*
import java.util.*
import javax.inject.Inject

@ActivityScoped
internal class TourListFragment @Inject internal constructor() : DaggerFragment(), TourListContact.View {
    object ArgumentKey {
        const val CONTENT_TYPE = "content_type_code"
    }

//    @Inject
//    lateinit var presenter: TourListPresenter
//
     var contentType: Int? = null
    @Inject set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.fragment_tour_list, null, false)

//        contentType?.let {
//            presenter.loadTourList(it)
//        }

        return rootView
    }

    override fun initTourListUi(areaTourList: ArrayList<AreaTour>) {
        tourListRecyclerView.adapter = TourListRecyclerViewAdapter(areaTourList)
        tourListRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()
//        presenter.takeView(this)
    }

    override fun showTourDetailsUi(areaTour: AreaTour) {
    }
}