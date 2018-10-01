package com.work.hany.playinseoul.toursearch

import android.util.Log
import com.work.hany.playinseoul.model.DataHandler
import com.work.hany.playinseoul.network.Area
import com.work.hany.playinseoul.network.Result
import com.work.hany.playinseoul.toursearch.vo.SearchItem
import com.work.hany.playinseoul.toursearch.vo.SearchSection
import com.work.hany.playinseoul.toursearch.vo.SearchSection.SearchItemType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import javax.inject.Inject

class SearchPresenter @Inject constructor(private var dataHandler: DataHandler): SearchContract.Presenter {
    private lateinit var searchView: SearchContract.View
    private var selectedTagList = ArrayList<String>()


    override fun takeView(view: SearchContract.View) {
        this.searchView = view
    }

    override fun dropView() {

    }

    override fun loadCategoryCode(searchItem: SearchItem, type: SearchItemType) {

        var params = HashMap<String, String>()

        when (type) {
            SearchItemType.TOUR -> params["contentTypeId"] = searchItem.contentTypeId

            SearchItemType.TOUR_LARGE -> { //cat1
                params["contentTypeId"] = searchItem.contentTypeId
                params["cat1"] = searchItem.cat1

            }

            SearchItemType.TOUR_MEDIUM -> { //cat2
                params["contentTypeId"] = searchItem.contentTypeId
                params["cat1"] = searchItem.cat1
                params["cat2"] = searchItem.cat2

            }

            SearchItemType.TOUR_SMALL -> { //cat1
                params["contentTypeId"] = searchItem.contentTypeId
                params["cat1"] = searchItem.cat1
                params["cat2"] = searchItem.cat2
                params["cat3"] = searchItem.cat3

            }

            SearchItemType.EMPTY -> return
        }

        dataHandler.getCategoryCodeList(params).enqueue(object : Callback<Result<ArrayList<Area>>> {

            override fun onResponse(call: Call<Result<ArrayList<Area>>>, response: Response<Result<ArrayList<Area>>>) {
                if (!call.isCanceled) {
                    val categoryCodeList = response.body()!!.response.body.items.data
                    searchView.initAreaCodeListUi(SearchItemType.TOUR, categoryCodeList)
                }

            }

            override fun onFailure(call: Call<Result<ArrayList<Area>>>?, t: Throwable?) {
                //TODO 리스트가 한개만 내려오면 이렇더라;;
                dataHandler.getCategoryCode(params).enqueue(object : Callback<Result<Area>> {

                    override fun onResponse(call: Call<Result<Area>>, response: Response<Result<Area>>) {
                        if (!call.isCanceled) {
                            val categoryCode = response.body()!!.response.body.items.data
                            var areaList = ArrayList<Area>()
                            areaList.add(categoryCode)
                            searchView.initAreaCodeListUi(SearchItemType.TOUR, areaList)
                        }

                    }

                    override fun onFailure(call: Call<Result<Area>>?, t: Throwable?) {
                        Log.d("HANY_TAG","onFailure")
                    }

                })
            }

        })


    }
    override fun loadAreaCode(depth: Int, searchItem: SearchItem) {
        var params = HashMap<String, String>()

        when (depth) {
            2 -> {
                params["areaCode"] = searchItem.areaCode
                params["sigunguCode"] = searchItem.cityCode
            }

            1 -> {
                params["areaCode"] = searchItem.areaCode

            }

            else -> params["areaCode"] = ""

        }

        dataHandler.getAreaCodeList(params).enqueue(object : Callback<Result<ArrayList<Area>>> {

            override fun onResponse(call: Call<Result<ArrayList<Area>>>, response: Response<Result<ArrayList<Area>>>) {
                if (!call.isCanceled) {
                    val areaCodeList = response.body()!!.response.body.items.data
                   var it = areaCodeList.iterator()
                    while (it.hasNext()) {
                        var areaCode = it.next()
                        areaCode.depth = depth
                    }

                    searchView.initAreaCodeListUi(SearchItemType.AREA, areaCodeList)
                }

            }

            override fun onFailure(call: Call<Result<ArrayList<Area>>>?, t: Throwable?) {
                dataHandler.getAreaCode(params).enqueue(object : Callback<Result<Area>> {

                    override fun onResponse(call: Call<Result<Area>>, response: Response<Result<Area>>) {
                        if (!call.isCanceled) {
                            val areaCode = response.body()!!.response.body.items.data
                            areaCode.depth = depth

                            var areaList = ArrayList<Area>()
                            areaList.add(areaCode)
                            searchView.initAreaCodeListUi(SearchItemType.AREA, areaList)

                        }

                    }

                    override fun onFailure(call: Call<Result<Area>>?, t: Throwable?) {
                        Log.d("HANY_TAG", "onFailure")
                    }

                })
            }

        })


    }

//    override fun deleteSelectedTag(name: String) {
//        var index = hasSelectedTagAt(name)
//        if(index != -1){
//            selectedTagList.removeAt(index)
//        }
//
//
//    }

    override fun deleteSelectedTag(type: SearchSection.SearchItemType, depth: Int, searchItem: SearchItem) {
        var beforeDepth = depth - 1
        when (type) {
            SearchSection.SearchItemType.AREA -> {
                loadAreaCode(beforeDepth, searchItem)
            }
        }


    }

    private fun hasSelectedTagAt(name: String): Int {
        var index = -1 //empty
        val it = selectedTagList.listIterator()
        while (it.hasNext()) {
            val tag = it.next()
            if (tag == name) {
                index = it.previousIndex()
            }
        }
        return index

    }

    override fun addSelectedTag(name: String) {
        if(hasSelectedTagAt(name) == -1){
            selectedTagList.add(name)
        }
    }
}