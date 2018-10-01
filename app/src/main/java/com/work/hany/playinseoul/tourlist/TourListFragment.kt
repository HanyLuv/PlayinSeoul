package com.work.hany.playinseoul.tourlist

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.work.hany.playinseoul.R
import com.work.hany.playinseoul.di.ActivityScoped
import com.work.hany.playinseoul.network.AreaTour
import com.work.hany.playinseoul.tourdetail.DetailActivity
import com.work.hany.playinseoul.tourlist.adapter.TourListRecyclerViewAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_tour_list.*
import java.util.*
import javax.inject.Inject

@ActivityScoped
internal class TourListFragment @Inject internal constructor() : DaggerFragment(), TourListContract.View {
    object ArgumentKey {
        const val CONTENT_TYPE = "content_type_code"
    }

    @Inject
    lateinit var presenter: TourListPresenter
//
    var contentType: Int? = null
    @Inject set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.fragment_tour_list, null, false)

        contentType?.let {
            presenter.loadTourList(it)
        }

        return rootView
    }

    override fun initTourListUi(areaTourList: ArrayList<AreaTour>) {
        tourListRecyclerView.layoutManager = LinearLayoutManager(context)
        tourListRecyclerView.adapter = TourListRecyclerViewAdapter(areaTourList, itemListener)
    }

    private var itemListener : TourListRecyclerViewAdapter.ItemListener = object : TourListRecyclerViewAdapter.ItemListener {
        override fun onTourClicked(tour: AreaTour) {
            presenter.openTourDetails(tour)
        }
    }


    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun showTourDetailsUi(areaTour: AreaTour) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_TOUR_ID, areaTour)
        startActivity(intent)
    }
}