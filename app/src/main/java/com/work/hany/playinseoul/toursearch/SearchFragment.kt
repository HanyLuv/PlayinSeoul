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
import com.work.hany.playinseoul.model.ContentType
import com.work.hany.playinseoul.network.Area
import com.work.hany.playinseoul.toursearch.adapter.SearchAttrRecyclerViewAdapter
import com.work.hany.playinseoul.toursearch.adapter.SearchRecyclerViewAdapter
import com.work.hany.playinseoul.toursearch.vo.SearchItem
import com.work.hany.playinseoul.toursearch.vo.SearchSection
import com.work.hany.playinseoul.toursearch.vo.SearchSection.SearchItemType
import dagger.android.support.DaggerFragment
import javax.inject.Inject

@ActivityScoped
class SearchFragment@Inject internal constructor(): DaggerFragment(), SearchContract.View {

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

    override fun initAreaCodeListUi(type: SearchSection.SearchItemType, items: ArrayList<Area>) {
        searchAdapter.updateSearchItem(type, items)
    }


    //어떤 요청인지 봐야겟다.
    private var itemListener: SearchAttrRecyclerViewAdapter.ItemListener = object : SearchAttrRecyclerViewAdapter.ItemListener {

        override fun onItemClicked(item: Area, type: SearchItemType) {
            //이미 선택 된것은 로드하지 않는다.
            if(type == SearchItemType.AREA && item.name != "세종특별자치시"){
                searchActivity.updateSelectedTagUi(type, item)
                presenter.loadAreaCode(item.depth + 1, searchActivity.searchItem)


            } else if(type == SearchItemType.TOUR){
                searchActivity.updateSelectedTagUi(type, item)
                var categoryType = searchActivity.nextSelectedCategryTagType()
                presenter.loadCategoryCode(searchActivity.searchItem, categoryType)

            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.fragment_search, null, false)

        var searchItems: ArrayList<SearchSection> = ArrayList()
        searchItems.add(SearchSection(SearchItemType.AREA, ArrayList()))
        val areaTour = ArrayList<Area>() // TODO 데이터 만드는 클래스로 이동하자:)
        areaTour.add(Area(ContentType.TOUR.code.toString(), ContentType.TOUR.tagName, ContentType.TOUR.code))
        areaTour.add(Area(ContentType.CULTURE.code.toString(), ContentType.CULTURE.tagName, ContentType.CULTURE.code))
        areaTour.add(Area(ContentType.TRAVEL_COURSE.code.toString(), ContentType.TRAVEL_COURSE.tagName, ContentType.TRAVEL_COURSE.code))
        areaTour.add(Area(ContentType.FESTIVAL.code.toString(), ContentType.FESTIVAL.tagName, ContentType.FESTIVAL.code))
        areaTour.add(Area(ContentType.REPORTS.code.toString(), ContentType.REPORTS.tagName, ContentType.REPORTS.code))
        areaTour.add(Area(ContentType.SHOPPING.code.toString(), ContentType.SHOPPING.tagName, ContentType.SHOPPING.code))
        areaTour.add(Area(ContentType.STAY.code.toString(), ContentType.STAY.tagName, ContentType.STAY.code))
        areaTour.add(Area(ContentType.FOOD.code.toString(), ContentType.FOOD.tagName, ContentType.FOOD.code))
        searchItems.add(SearchSection(SearchItemType.CITY, ArrayList()))
        searchItems.add(SearchSection(SearchItemType.TOUR, areaTour))
        searchItems.add(SearchSection(SearchItemType.TOUR_MEDIUM, ArrayList()))
        searchItems.add(SearchSection(SearchItemType.TOUR_SMALL, ArrayList()))

        searchAdapter = SearchRecyclerViewAdapter(searchItems, itemListener)

        searchRecyclerView = rootView.findViewById(R.id.searchRecyclerView)
        searchRecyclerView.layoutManager = LinearLayoutManager(context)
        searchRecyclerView.adapter = searchAdapter


        presenter.loadAreaCode(0,searchActivity.searchItem)

        return rootView
    }

    open fun deleteSelectedTag(type: SearchSection.SearchItemType, depth: Int, searchItem: SearchItem) {
        presenter.deleteSelectedTag(type, depth, searchItem)
    }

}