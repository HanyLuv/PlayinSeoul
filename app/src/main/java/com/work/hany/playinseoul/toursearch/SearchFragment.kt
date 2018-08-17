package com.work.hany.playinseoul.toursearch

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.work.hany.playinseoul.R
import com.work.hany.playinseoul.di.ActivityScoped
import com.work.hany.playinseoul.network.Area
import com.work.hany.playinseoul.toursearch.adapter.SearchAttrRecyclerViewAdapter
import com.work.hany.playinseoul.toursearch.adapter.SearchRecyclerViewAdapter
import com.work.hany.playinseoul.toursearch.vo.SearchItem
import com.work.hany.playinseoul.toursearch.vo.SearchItem.SearchItemType
import dagger.android.support.DaggerFragment
import java.util.ArrayList
import javax.inject.Inject

@ActivityScoped
class SearchFragment@Inject internal constructor(): DaggerFragment(), SearchContact.View {

    @Inject
    lateinit var presenter: SearchPresenter

    private lateinit var searchAdapter: SearchRecyclerViewAdapter
    private lateinit var searchRecyclerView: RecyclerView

    private lateinit var searchActivity: SearchActivity
    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        searchActivity = activity as SearchActivity
    }

    override fun initAreaCodeListUi(type: SearchItem.SearchItemType, items: ArrayList<Area>) {
        searchAdapter.updateSearchItem(type, items)
    }


    //어떤 요청인지 봐야겟다.
    private var itemListener: SearchAttrRecyclerViewAdapter.ItemListener = object : SearchAttrRecyclerViewAdapter.ItemListener {

        override fun onItemClicked(item: Area, type: SearchItemType) {
            searchActivity.updateSelectedTagUi(item)
            presenter.loadAreaCode(item.code,type)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.fragment_search, null, false)
        var searchItems = ArrayList<SearchItem>()
        searchItems.add(SearchItem(SearchItemType.AREA, ArrayList()))
        searchItems.add(SearchItem(SearchItemType.TOUR, ArrayList()))
        searchAdapter = SearchRecyclerViewAdapter(searchItems, itemListener)

        searchRecyclerView = rootView.findViewById(R.id.searchRecyclerView)
        searchRecyclerView.layoutManager = LinearLayoutManager(context)
        searchRecyclerView.adapter = searchAdapter


        presenter.loadAreaCode()

        return rootView
    }
}