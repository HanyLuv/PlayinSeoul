package com.work.hany.playinseoul.toursearch

import com.work.hany.playinseoul.BasePresenter
import com.work.hany.playinseoul.BaseView
import com.work.hany.playinseoul.network.Area
import com.work.hany.playinseoul.toursearch.vo.SearchItem
import com.work.hany.playinseoul.toursearch.vo.SearchSection
import java.util.ArrayList


class SearchContract {
    interface View : BaseView<Presenter> {
        fun initAreaCodeListUi(type: SearchSection.SearchItemType, items: ArrayList<Area>)
//        fun showTourDetailsUi(areaTour: AreaTour)
//        void setLoadingIndicator(boolean active);
    }


    interface Presenter : BasePresenter<View> {
//        fun openTourDetails(areaTour: AreaTour)
//        fun deleteSelectedTag(name: String)
        fun deleteSelectedTag(type: SearchSection.SearchItemType, depth: Int ,searchItem: SearchItem)
        fun addSelectedTag(name: String)
        fun loadCategoryCode(searchItem: SearchItem, type: SearchSection.SearchItemType)
        fun loadAreaCode(depth: Int,searchItem: SearchItem)
    }
}