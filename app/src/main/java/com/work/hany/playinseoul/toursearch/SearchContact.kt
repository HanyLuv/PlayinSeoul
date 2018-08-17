package com.work.hany.playinseoul.toursearch

import com.work.hany.playinseoul.BasePresenter
import com.work.hany.playinseoul.BaseView
import com.work.hany.playinseoul.model.ContentType
import com.work.hany.playinseoul.network.Area
import com.work.hany.playinseoul.network.AreaTour
import com.work.hany.playinseoul.toursearch.vo.SearchItem
import java.util.ArrayList


class SearchContact {
    interface View : BaseView<Presenter> {
        fun initAreaCodeListUi(type: SearchItem.SearchItemType, items: ArrayList<Area>)
//        fun showTourDetailsUi(areaTour: AreaTour)
//        void setLoadingIndicator(boolean active);
    }


    interface Presenter : BasePresenter<View> {
//        fun openTourDetails(areaTour: AreaTour)
        fun loadAreaCode()
        fun loadAreaCode(areaCode: String,type: SearchItem.SearchItemType)
        fun deleteSelectedTag(name: String)
        fun addSelectedTag(name: String)

    }
}